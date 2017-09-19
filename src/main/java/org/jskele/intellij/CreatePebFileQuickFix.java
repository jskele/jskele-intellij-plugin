package org.jskele.intellij;

import com.intellij.psi.PsiMethod;
import org.jetbrains.annotations.NotNull;

public class CreatePebFileQuickFix extends CreateFileQuickFix {

	public CreatePebFileQuickFix(@NotNull PsiMethod psiMethod) {
		super(psiMethod);
	}

	@NotNull
	@Override
	public String getFilePSuffix() {
		return "peb";
	}
}
