package org.jskele.intellij;

import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiMethod;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

abstract class CreateFileQuickFix extends BaseIntentionAction {

	private PsiMethod psiMethod;

	CreateFileQuickFix(@NotNull PsiMethod psiMethod) {
		this.psiMethod = psiMethod;
	}

	@Nls
	@NotNull
	@Override
	public String getFamilyName() {
		return "JSkele";
	}

	@NotNull
	@Override
	public String getText() {
		return "Create " + getFilePSuffix().toUpperCase() + " file";
	}

	@NotNull
	public abstract String getFilePSuffix();

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
		return true;
	}

	@Override
	public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
		ApplicationManager.getApplication().runWriteAction(() -> {
			psiMethod.getContainingFile().getContainingDirectory().createFile(psiMethod.getName() + "." + getFilePSuffix());
		});
	}

}
