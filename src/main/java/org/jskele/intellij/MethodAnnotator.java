package org.jskele.intellij;

import static org.jskele.intellij.JSkeleUtil.hasGenerateSqlAnnotation;
import static org.jskele.intellij.JSkeleUtil.hasSqlFile;
import static org.jskele.intellij.core.Constants.MISSING_ANNOTATIONS_ERROR;

import java.util.stream.Stream;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.project.DumbService;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiIdentifier;
import com.intellij.psi.PsiMethod;
import org.jetbrains.annotations.NotNull;
import org.jskele.intellij.core.Constants;

public class MethodAnnotator implements Annotator {
	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		if (DumbService.isDumb(element.getProject())) {
			return;
		}
		if (isPsiMethodIdentifier(element)) {
			boolean hasSkeleInterface = JSkeleUtil.hasSkeleInterface(element);
			if (hasSkeleInterface) {
				PsiMethod selectedMethod = (PsiMethod) element.getContext();
				if (selectedMethod != null) {
					boolean hasGenerateSqlAnnotation = hasGenerateSqlAnnotation(selectedMethod);
					boolean hasSqlFile = hasSqlFile(element.getProject(), selectedMethod);
					if (hasSqlFile && hasGenerateSqlAnnotation) {
						addError(holder, selectedMethod, Constants.BOTH_ANNOTATIONS_PRESENT_ERROR, addFixes(selectedMethod));
					} else if (!hasSqlFile && !hasGenerateSqlAnnotation) {
						addError(holder, selectedMethod, MISSING_ANNOTATIONS_ERROR, addFixes(selectedMethod));
					}
				}
			}
		}
	}

	@NotNull
	private IntentionAction[] addFixes(PsiMethod selectedMethod) {
		return new IntentionAction[] {new CreateSqlFileQuickFix(selectedMethod),
				new CreatePebFileQuickFix(selectedMethod)};
	}

	private boolean isPsiMethodIdentifier(@NotNull PsiElement element) {
		return element instanceof PsiIdentifier && element.getContext() instanceof PsiMethod;
	}

	private void addError(@NotNull AnnotationHolder annotationHolder, PsiMethod selectedMethod, String message, IntentionAction... fixes) {
		if (selectedMethod.getNameIdentifier() != null) {
			TextRange textRange = selectedMethod.getNameIdentifier().getTextRange();
			TextRange range = new TextRange(textRange.getStartOffset(),
					textRange.getEndOffset());
			Annotation annotation = annotationHolder.createErrorAnnotation(range, message);
			Stream.of(fixes)
					.forEach(fix -> annotation.registerFix(fix));
		}
	}

}
