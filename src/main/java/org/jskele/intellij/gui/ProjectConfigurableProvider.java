package org.jskele.intellij.gui;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurableProvider;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jskele.intellij.gui.ProjectConfigurableImpl;

public class ProjectConfigurableProvider extends ConfigurableProvider {

	@NotNull
	private final Project project;

	public ProjectConfigurableProvider(@NotNull Project project) {
		this.project = project;
	}

	@Nullable
	@Override
	public Configurable createConfigurable() {
		return new ProjectConfigurableImpl(project);
	}

}
