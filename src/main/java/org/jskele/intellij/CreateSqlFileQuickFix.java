package org.jskele.intellij;

import com.intellij.psi.PsiMethod;
import org.jetbrains.annotations.NotNull;

public class CreateSqlFileQuickFix extends CreateFileQuickFix {

	public CreateSqlFileQuickFix(@NotNull PsiMethod psiMethod) {
		super(psiMethod);
	}

	@NotNull
	@Override
	public String getFilePSuffix() {
		return "sql";
	}
}
