package org.dice.rcp.project;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dice.rcp.DiceActivator;
import org.dice.rcp.project.nature.DiceProjectNature;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

/**
 * The Class creates a new DICE project resource in the workspace that contains
 * the DICE template model
 */
public class DiceNewProjectWizard extends BasicNewProjectResourceWizard {

	private final String templateFolder = "/dice_project_template";

	@Override
	public boolean performFinish() {
		if (!super.performFinish()) {
			return false;
		}

		IProject newProject = getNewProject();

		try {
			addDiceNature(newProject);
			copyDiceTemplate(newProject);
			newProject.refreshLocal(IProject.DEPTH_INFINITE, new NullProgressMonitor());
		} catch (CoreException | IOException e) {
		}

		return true;
	}

	private void addDiceNature(IProject newProject) throws CoreException {
		IProjectDescription description = newProject.getDescription();
		if (description == null) {
			return;
		}

		List<String> natures = new ArrayList<>();
		natures.addAll(Arrays.asList(description.getNatureIds()));
		natures.add(DiceProjectNature.ID);

		description.setNatureIds(natures.toArray(new String[0]));
		description.setComment("This is a DICE template project created automatically with a sample UML model");

		newProject.setDescription(description, new NullProgressMonitor());
	}

	private void copyDiceTemplate(IProject newProject) throws IOException {
		URL sourceUrl = Platform.getBundle(DiceActivator.PLUGIN_ID).getResource(templateFolder);
		sourceUrl = FileLocator.toFileURL(sourceUrl);
		File source = new File(sourceUrl.getPath());

		Path sourcePath = source.toPath();
		Path targetPath = newProject.getLocation().toFile().toPath();

		Files.walkFileTree(sourcePath, new CopyPathVisitor(sourcePath, targetPath));
	}

	private class CopyPathVisitor extends SimpleFileVisitor<Path> {
		private final Path fromPath;
		private final Path toPath;
		private final CopyOption copyOption;

		public CopyPathVisitor(Path fromPath, Path toPath) {
			this.fromPath = fromPath;
			this.toPath = toPath;
			this.copyOption = StandardCopyOption.REPLACE_EXISTING;
		}

		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
			Path targetPath = toPath.resolve(fromPath.relativize(dir));
			if (!Files.exists(targetPath)) {
				Files.createDirectory(targetPath);
			}
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			Files.copy(file, toPath.resolve(fromPath.relativize(file)), copyOption);
			return FileVisitResult.CONTINUE;
		}

	}

}