package org.jskele.intellij;

import static java.util.Arrays.asList;
import static org.jskele.intellij.Constants.GENERATE_SQL_ANNOTATION;
import static org.jskele.intellij.Constants.ORG_JSKELE_LIBS_DAO_DAO;

import java.util.Optional;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.InheritanceUtil;

public final class JSkeleUtil {

	private JSkeleUtil() {}

	public static boolean hasSkeleInterface(PsiElement psiElement) {
		if (psiElement.getContext() instanceof PsiMethod) {
			PsiMethod selectedMethod = (PsiMethod) psiElement.getContext();
			PsiClass containingClass = selectedMethod.getContainingClass();
			if (containingClass != null) {
				return InheritanceUtil.isInheritor(containingClass, ORG_JSKELE_LIBS_DAO_DAO);
			}
		}
		return false;
	}

	public static boolean hasGenerateSqlAnnotation(PsiMethod psiMethod) {
		Optional<PsiAnnotation> hasGenerateSqlAnnotation = asList(psiMethod.getModifierList().getAnnotations())
				.stream()
				.filter(psiAnnotation -> GENERATE_SQL_ANNOTATION.equals(psiAnnotation.getQualifiedName()))
				.findFirst();
		return hasGenerateSqlAnnotation.isPresent();
	}

	public static boolean hasSqlFile(Project project, PsiMethod psiMethod) {
		String resourceName = getSqlFileName(psiMethod);
		final GlobalSearchScope scope = GlobalSearchScope.allScope(project);
		PsiFile[] filesByName = FilenameIndex.getFilesByName(project, psiMethod.getName() + ".sql", scope);
		for (PsiFile sqlFile : filesByName) {
			VirtualFile virtualFile = sqlFile.getVirtualFile();
			if (virtualFile != null && virtualFile.getPath().endsWith(resourceName)) {
				return true;
			}
		}
		return false;
	}

	private static String getSqlFileName(PsiMethod psiMethod) {
		String packageName = ((PsiJavaFile) psiMethod.getContainingFile()).getPackageName();
		String methodName = psiMethod.getName();
		return "/" + packageName.replace(".", "/") + "/" + methodName + ".sql";
	}
}
