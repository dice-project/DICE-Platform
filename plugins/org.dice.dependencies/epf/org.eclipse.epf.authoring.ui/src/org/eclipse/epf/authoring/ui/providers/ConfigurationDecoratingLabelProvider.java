package org.eclipse.epf.authoring.ui.providers;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;
import org.eclipse.epf.library.configuration.ConfigurationData;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;

public class ConfigurationDecoratingLabelProvider extends
		DecoratingLabelProvider {

	private MethodConfiguration config;
	private Font italicFont = null;

	public ConfigurationDecoratingLabelProvider(MethodConfiguration config,
			AdapterFactory adapterFactory) {
		super(new ConfigurationLabelProvider(config, adapterFactory),
				new MethodElementLabelDecorator());
		this.config = config;
	}

	public Font getFont(Object element) {
		if(element instanceof IWrapperItemProvider){
			element = TngUtil.unwrap(element);
		}
		if (element instanceof ContentElement) {
			ConfigurationData configData = ConfigurationHelper.getDelegate().getConfigurationData(config);
			if (configData.isSuppressed((ContentElement) element)) {
				return getItalicFont();
			}
		}
		return super.getFont(element);
	}

	private Font getItalicFont() {
		if (italicFont == null) {
			italicFont = createFont(SWT.ITALIC);
		}

		return italicFont;
	}

	private Font createFont(int style) {
		FontData[] fontdata = Display.getCurrent().getSystemFont()
				.getFontData();
		for (FontData data : fontdata) {
			data.setStyle(style);
		}

		return new Font(Display.getCurrent(), fontdata);
	}

	@Override
	public void dispose() {
		super.dispose();
		if (italicFont != null) {
			italicFont.dispose();
		}
	}
	
}
