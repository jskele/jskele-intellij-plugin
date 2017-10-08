package org.jskele.intellij.gui;

import javax.swing.*;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jskele.intellij.core.ProjectSettings;

public class ProjectConfigurableImpl implements SearchableConfigurable, Configurable.NoScroll {

	static final String ID = "settings.jskele.project";

	@NotNull
	private final Project project;

	@NotNull
	private final ProjectSettings settings;

	private ProjectSettingsPane pane;

	public ProjectConfigurableImpl(@NotNull final Project project) {
		this.project = project;
		this.settings = ProjectSettings.getInstance(project);
	}

	@NotNull
	@Override
	public String getId() {
		return ID;
	}

	@Nullable
	@Override
	public Runnable enableSearch(String option) {
		return null;
	}

	@Nls
	@Override
	public String getDisplayName() {
		return "JSkele settings display name";
	}

	@Nullable
	@Override
	public String getHelpTopic() {
		return null;
	}

	@Nullable
	@Override
	public JComponent createComponent() {
		if (pane == null) {
			pane = createSettingsPane();
		}
		return pane;
	}

	private ProjectSettingsPane createSettingsPane() {
		return new ProjectSettingsPane(project);
	}

	@Override
	public boolean isModified() {
		return pane.isModified(settings);
	}

	@Override
	public void apply() throws ConfigurationException {
		pane.apply(settings);
	}

	@Override
	public void reset() {
		pane.reset(settings);
	}

	@Override
	public void disposeUIResources() {

	}
}
