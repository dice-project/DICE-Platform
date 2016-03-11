//------------------------------------------------------------------------------
// Copyright (c) 2005, 2006 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.authoring.gef.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.Image;

import com.ibm.icu.text.BreakIterator;
import com.ibm.icu.util.StringTokenizer;

/**
 * Provides support for text wrap in a label.
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 * 
 */
public class WrappableLabel extends Figure implements PositionConstants {

	private static String ELLIPSIS = "..."; //$NON-NLS-1$

	private Image icon;

	private String text = ""; //$NON-NLS-1$

	private String subStringText;

	private Dimension textSize;

	private Dimension subStringTextSize;

	private Dimension iconSize = new Dimension(0, 0);

	private Point iconLocation;

	private Point textLocation;

	private int textAlignment = CENTER;

	private int iconAlignment = CENTER;

	private int labelAlignment = CENTER;

	private int textPlacement = SOUTH;

	private int iconTextGap = 3;

	private static final int FLAG_SELECTED = MAX_FLAG << 1,
			FLAG_HASFOCUS = MAX_FLAG << 2, FLAG_WRAP = MAX_FLAG << 5,
			FLAG_WRAP_ALIGN = MAX_FLAG << 6;

	// The primary dimensions used to calculate text size.
	private Dimension textDimension = new Dimension(-1, -1);

	private Dimension prefSizeDimension = new Dimension(-1, -1);

	private int wrapWidth;

	public WrappableLabel() {
		super();
	}

	/**
	 * Construct a Label with passed String as its text.
	 * 
	 * @param s
	 *            the label text
	 * @since 2.0
	 */
	public WrappableLabel(String s) {
		setText(s);
	}

	/**
	 * Construct a WrappedLabel with passed Image as its icon.
	 * 
	 * @param i
	 *            the WrappedLabel image
	 * @since 2.0
	 */
	public WrappableLabel(Image i) {
		setIcon(i);
	}

	/**
	 * Construct a WrappedLabel with passed String as text and passed Image as
	 * its icon.
	 * 
	 * @param s
	 *            the WrappedLabel text
	 * @param i
	 *            the WrappedLabel image
	 * @since 2.0
	 */
	public WrappableLabel(String s, Image i) {
		setText(s);
		setIcon(i);
	}

	private void alignOnHeight(Point loc, Dimension size, int alignment) {
		Insets insets = getInsets();
		switch (alignment) {
		case TOP:
			loc.y = insets.top;
			break;
		case BOTTOM:
			loc.y = bounds.height - size.height - insets.bottom;
			break;
		default:
			loc.y = (bounds.height - size.height) / 2;
		}
	}

	private void alignOnWidth(Point loc, Dimension size, int alignment) {
		Insets insets = getInsets();
		switch (alignment) {
		case LEFT:
			loc.x = insets.left;
			break;
		case RIGHT:
			loc.x = bounds.width - size.width - insets.right;
			break;
		default:
			loc.x = (bounds.width - size.width) / 2;
		}
	}

	private void calculateAlignment() {
		switch (textPlacement) {
		case EAST:
		case WEST:
			alignOnHeight(textLocation, getTextSize(), textAlignment);
			alignOnHeight(iconLocation, iconSize, iconAlignment);
			break;
		case NORTH:
		case SOUTH:
			alignOnWidth(textLocation, getSubStringTextSize(), textAlignment);
			alignOnWidth(iconLocation, iconSize, iconAlignment);
			break;
		}
	}

	/**
	 * Calculates the size of the WrappedLabel using the passed Dimension as the
	 * size of the WrappedLabel's text.
	 * 
	 * @param txtSize
	 *            the precalculated size of the WrappedLabel's text
	 * @return the WrappedLabel's size
	 * @since 2.0
	 */
	protected Dimension calculateLabelSize(Dimension txtSize) {
		int gap = iconTextGap;
		if (getIcon() == null || getText().equals("")) //$NON-NLS-1$
			gap = 0;
		Dimension d = new Dimension(0, 0);
		if (textPlacement == WEST || textPlacement == EAST) {
			d.width = iconSize.width + gap + txtSize.width;
			d.height = Math.max(iconSize.height, txtSize.height);
		} else {
			d.width = Math.max(iconSize.width, txtSize.width);
			d.height = iconSize.height + gap + txtSize.height;
		}
		return d;
	}

	private void calculateLocations() {
		textLocation = new Point();
		iconLocation = new Point();

		calculatePlacement();
		calculateAlignment();
		Dimension offset = getSize().getDifference(getPreferredSize());
		offset.width += getTextSize().width - getSubStringTextSize().width;
		switch (labelAlignment) {
		case CENTER:
			offset.scale(0.5f);
			break;
		case LEFT:
			offset.scale(0.0f);
			break;
		case RIGHT:
			offset.scale(1.0f);
			break;
		case TOP:
			offset.height = 0;
			offset.scale(0.5f);
			break;
		case BOTTOM:
			offset.height = offset.height * 2;
			offset.scale(0.5f);
			break;
		default:
			offset.scale(0.5f);
			break;
		}

		switch (textPlacement) {
		case EAST:
		case WEST:
			offset.height = 0;
			break;
		case NORTH:
		case SOUTH:
			offset.width = 0;
			break;
		}

		textLocation.translate(offset);
		iconLocation.translate(offset);
	}

	private void calculatePlacement() {
		int gap = iconTextGap;
		if (icon == null || text.equals("")) //$NON-NLS-1$
			gap = 0;
		Insets insets = getInsets();

		switch (textPlacement) {
		case EAST:
			iconLocation.x = insets.left;
			textLocation.x = iconSize.width + gap + insets.left;
			break;
		case WEST:
			textLocation.x = insets.left;
			iconLocation.x = getSubStringTextSize().width + gap + insets.left;
			break;
		case NORTH:
			textLocation.y = insets.top;
			iconLocation.y = getTextSize().height + gap + insets.top;
			break;
		case SOUTH:
			textLocation.y = iconSize.height + gap + insets.top;
			iconLocation.y = insets.top;
		}
	}

	/**
	 * Calculates the size of the Label's text size. The text size calculated
	 * takes into consideration if the Label's text is currently truncated. If
	 * text size without considering current truncation is desired, use
	 * {@link #calculateTextSize()}.
	 * 
	 * @return the size of the label's text, taking into account truncation
	 * @since 2.0
	 */
	protected Dimension calculateSubStringTextSize() {
		return FigureUtilities.getTextExtents(getSubStringText(), getFont());
	}

	/**
	 * Calculates and returns the size of the Label's text. Note that this
	 * Dimension is calculated using the Label's full text, regardless of
	 * whether or not its text is currently truncated. If text size considering
	 * current truncation is desired, use {@link #calculateSubStringTextSize()}.
	 * 
	 * @return the size of the label's text, ignoring truncation
	 * @since 2.0
	 */
	protected Dimension calculateTextSize() {
		// return FigureUtilities.getTextExtents(getText(), getFont());
		return FigureUtilities.getTextExtents(getWrappedText(getSize().width,
				getSize().height), getFont());

	}

	private void clearLocations() {
		iconLocation = textLocation = null;
	}

	/**
	 * Returns the Label's icon.
	 * 
	 * @return the label icon
	 * @since 2.0
	 */
	public Image getIcon() {
		return icon;
	}

	/**
	 * Returns the current alignment of the Label's icon. The default is
	 * {@link PositionConstants#CENTER}.
	 * 
	 * @return the icon alignment
	 * @since 2.0
	 */
	public int getIconAlignment() {
		return iconAlignment;
	}

	/**
	 * Returns the bounds of the Label's icon.
	 * 
	 * @return the icon's bounds
	 * @since 2.0
	 */
	public Rectangle getIconBounds() {
		Rectangle bounds = getBounds();
		return new Rectangle(bounds.getLocation().translate(getIconLocation()),
				iconSize);
	}

	/**
	 * Returns the location of the Label's icon relative to the Label.
	 * 
	 * @return the icon's location
	 * @since 2.0
	 */
	protected Point getIconLocation() {
		if (iconLocation == null)
			calculateLocations();
		return iconLocation;
	}

	/**
	 * Returns the gap in pixels between the Label's icon and its text.
	 * 
	 * @return the gap
	 * @since 2.0
	 */
	public int getIconTextGap() {
		return iconTextGap;
	}

	/**
	 * @see IFigure#getMinimumSize(int, int)
	 */
	public Dimension getMinimumSize(int w, int h) {
		if (minSize != null)
			return minSize;
		minSize = new Dimension();
		if (getLayoutManager() != null)
			minSize.setSize(getLayoutManager().getMinimumSize(this, w, h));

		Dimension labelSize = calculateLabelSize(FigureUtilities
				.getTextExtents(ELLIPSIS, getFont()).intersect(
						FigureUtilities.getTextExtents(getText(), getFont())));
		Insets insets = getInsets();
		labelSize.expand(insets.getWidth(), insets.getHeight());
		minSize.union(labelSize);
		return minSize;
	}

	/**
	 * @see IFigure#getPreferredSize(int, int)
	 */
	public Dimension getPreferredSize(int wHint, int hHint) {
		if (prefSize == null || wHint != prefSizeDimension.width
				|| hHint != prefSizeDimension.height) {
			prefSize = calculateLabelSize(getTextSize(wHint, hHint));
			Insets insets = getInsets();
			prefSize.expand(insets.getWidth(), insets.getHeight());
			if (getLayoutManager() != null)
				prefSize.union(getLayoutManager().getPreferredSize(this, wHint,
						hHint));
		}
		if (wHint >= 0 && wHint < prefSize.width) {
			Dimension minSize = getMinimumSize(wHint, hHint);
			Dimension result = prefSize.getCopy();
			result.width = Math.min(result.width, wHint);
			result.width = Math.max(minSize.width, result.width);
			prefSizeDimension.width = result.width;
			prefSizeDimension.height = result.width;
			return result;
		}
		return prefSize;
	}

	/**
	 * Calculates the amount of the Label's current text will fit in the Label,
	 * including an elipsis "..." if truncation is required.
	 * 
	 * @return the substring
	 * @since 2.0
	 */
	public String getSubStringText() {
		if (subStringText != null)
			return subStringText;

		subStringText = text;
		Dimension shrink = getPreferredSize(getSize().width, getSize().height)
				.getDifference(getSize());
		Dimension effectiveSize = getTextSize().getExpanded(-shrink.width,
				-shrink.height);
		Font currentFont = getFont();
		int fontHeight = FigureUtilities.getFontMetrics(currentFont)
				.getHeight();
		int maxLines = (int) (effectiveSize.height / (double) fontHeight);

		StringBuffer accumlatedText = new StringBuffer();
		StringBuffer remainingText = new StringBuffer(getText());
		int i = 0, j = 0;

		while (remainingText.length() > 0 && j++ < maxLines) {
			i = getLineWrapPosition(remainingText.toString(), currentFont,
					effectiveSize.width);

			if (accumlatedText.length() > 0)
				accumlatedText.append("\n"); //$NON-NLS-1$

			if (i == 0 || (remainingText.length() > i && j == maxLines)) {
				int dotsWidth = FigureUtilities.getTextExtents(ELLIPSIS,
						currentFont).width;
				i = getLargestSubstringConfinedTo(remainingText.toString(),
						currentFont, Math.max(effectiveSize.width - dotsWidth,
								0));
				accumlatedText.append(remainingText.substring(0, i));
				accumlatedText.append(ELLIPSIS);
			} else
				accumlatedText.append(remainingText.substring(0, i));
			remainingText.delete(0, i);
		}
		return subStringText = accumlatedText.toString();
	}

	/**
	 * Returns the size of the Label's current text. If the text is currently
	 * truncated, the truncated text with its ellipsis is used to calculate the
	 * size.
	 * 
	 * @return the size of this label's text, taking into account truncation
	 * @since 2.0
	 */
	protected Dimension getSubStringTextSize() {
		if (subStringTextSize == null)
			subStringTextSize = calculateSubStringTextSize();
		return subStringTextSize;
	}

	/**
	 * Returns the text of the label. Note that this is the complete text of the
	 * label, regardless of whether it is currently being truncated. Call
	 * {@link #getSubStringText()} to return the label's current text contents
	 * with truncation considered.
	 * 
	 * @return the complete text of this label
	 * @since 2.0
	 */
	public String getText() {
		return text;
	}

	/**
	 * Returns the current alignment of the Label's text. The default text
	 * alignment is {@link PositionConstants#CENTER}.
	 * 
	 * @return the text alignment
	 */
	public int getTextAlignment() {
		return textAlignment;
	}

	/**
	 * Returns the bounds of the label's text. Note that the bounds are
	 * calculated using the label's complete text regardless of whether the
	 * label's text is currently truncated.
	 * 
	 * @return the bounds of this label's complete text
	 * @since 2.0
	 */
	public Rectangle getTextBounds() {
		Rectangle bounds = getBounds();
		return new Rectangle(bounds.getLocation().translate(getTextLocation()),
				textSize);
	}

	/**
	 * Returns the location of the label's text relative to the label.
	 * 
	 * @return the text location
	 * @since 2.0
	 */
	protected Point getTextLocation() {
		if (textLocation != null)
			return textLocation;
		calculateLocations();
		return textLocation;
	}

	/**
	 * Returns the current placement of the label's text relative to its icon.
	 * The default text placement is {@link PositionConstants#EAST}.
	 * 
	 * @return the text placement
	 * @since 2.0
	 */
	public int getTextPlacement() {
		return textPlacement;
	}

	/**
	 * Returns the size of the label's complete text. Note that the text used to
	 * make this calculation is the label's full text, regardless of whether the
	 * label's text is currently being truncated and is displaying an ellipsis.
	 * If the size considering current truncation is desired, call
	 * {@link #getSubStringTextSize()}.
	 * 
	 * @return the size of this label's complete text
	 * @since 2.0
	 */
	protected Dimension getTextSize() {
		if (textSize == null)
			textSize = calculateTextSize();
		return textSize;
	}

	/**
	 * Returns the size of the label's complete text. Note that the text used to
	 * make this calculation is the label's full text, regardless of whether the
	 * label's text is currently being truncated and is displaying an ellipsis.
	 * If the size considering current truncation is desired, call
	 * {@link #getSubStringTextSize()}.
	 * 
	 * @return the size of this label's complete text
	 * @since 2.0
	 */
	protected Dimension getTextSize(int width, int height) {
		if (textSize == null) {
			textSize = calculateTextSize(width, height);
			textDimension.width = width;
			textDimension.height = height;
		}
		return textSize;
	}

	/**
	 * @see IFigure#invalidate()
	 */
	public void invalidate() {
		prefSize = null;
		minSize = null;
		clearLocations();
		textSize = null;
		subStringTextSize = null;
		subStringText = null;
		super.invalidate();
	}

	/**
	 * Returns <code>true</code> if the label's text is currently truncated
	 * and is displaying an ellipsis, <code>false</code> otherwise.
	 * 
	 * @return <code>true</code> if the label's text is truncated
	 * @since 2.0
	 */
	public boolean isTextTruncated() {
		return !getSubStringTextSize().equals(getTextSize());
	}

	/**
	 * @see Figure#paintFigure(Graphics)
	 */
	protected void paintFigure(Graphics graphics) {
		if (isSelected()) {
			graphics.pushState();
			graphics.setBackgroundColor(ColorConstants.menuBackgroundSelected);
			graphics.fillRectangle(getSelectionRectangle());
			graphics.popState();
			graphics.setForegroundColor(ColorConstants.white);
		}
		if (hasFocus()) {
			graphics.pushState();
			graphics.setXORMode(true);
			graphics.setForegroundColor(ColorConstants.menuBackgroundSelected);
			graphics.setBackgroundColor(ColorConstants.white);
			graphics.drawFocus(getSelectionRectangle().resize(-1, -1));
			graphics.popState();
		}
		if (isOpaque())
			super.paintFigure(graphics);
		Rectangle bounds = getBounds();
		graphics.translate(bounds.x, bounds.y);
		if (icon != null)
			graphics.drawImage(icon, getIconLocation());

		if (!isEnabled()) {
			graphics.translate(1, 1);
			graphics.setForegroundColor(ColorConstants.buttonLightest);
			graphicsdrawText(graphics);
			graphics.drawText(getSubStringText(), getTextLocation());
			graphics.translate(-1, -1);
			graphics.setForegroundColor(ColorConstants.buttonDarker);
		}
		graphicsdrawText(graphics);
		graphics.translate(-bounds.x, -bounds.y);
	}

	/**
	 * Sets the label's icon to the passed image.
	 * 
	 * @param image
	 *            the new label image
	 * @since 2.0
	 */
	public void setIcon(Image image) {
		if (icon == image)
			return;
		icon = image;
		// Call repaint, in case the image dimensions are the same.
		repaint();
		if (icon == null)
			setIconDimension(new Dimension());
		else
			setIconDimension(new Dimension(image));
	}

	/**
	 * This method sets the alignment of the icon within the icon dimension. The
	 * dimension should be the same size as the Image itself, so calling this
	 * method should have no affect.
	 * 
	 * @param align
	 *            the icon alignment
	 * @since 2.0
	 */
	public void setIconAlignment(int align) {
		if (iconAlignment == align)
			return;
		iconAlignment = align;
		clearLocations();
		repaint();
	}

	/**
	 * Sets the label's icon size to the passed Dimension.
	 * 
	 * @param d
	 *            the new icon size
	 * @since 2.0
	 */
	public void setIconDimension(Dimension d) {
		if (d.equals(iconSize))
			return;
		iconSize = d;
		revalidate();
	}

	/**
	 * Sets the gap in pixels between the label's icon and text to the passed
	 * value. The default is 4.
	 * 
	 * @param gap
	 *            the gap
	 * @since 2.0
	 */
	public void setIconTextGap(int gap) {
		if (iconTextGap == gap)
			return;
		iconTextGap = gap;
		repaint();
		revalidate();
	}

	/**
	 * Sets the alignment of the entire label (icon and text). If this figure's
	 * bounds are larger than the size needed to display the label, the label
	 * will be aligned accordingly. Valid values are:
	 * <UL>
	 * <LI><EM>{@link PositionConstants#CENTER}</EM>
	 * <LI>{@link PositionConstants#TOP}
	 * <LI>{@link PositionConstants#BOTTOM}
	 * <LI>{@link PositionConstants#LEFT}
	 * <LI>{@link PositionConstants#RIGHT}
	 * </UL>
	 * 
	 * @param align
	 *            label alignment
	 */
	public void setLabelAlignment(int align) {
		if (labelAlignment == align)
			return;
		labelAlignment = align;
		clearLocations();
		repaint();
	}

	/**
	 * Sets the label's text.
	 * 
	 * @param s
	 *            the new label text
	 * @since 2.0
	 */
	public void setText(String s) {
		// "text" will never be null.
		if (s == null)
			s = ""; //$NON-NLS-1$
		if (text.equals(s))
			return;
		text = s;
		revalidate();
		repaint();
	}

	/**
	 * Sets the alignment of the Text relative to the icon. The text alignment
	 * must be orthogonal to the text placement. For example, if the placement
	 * is EAST, then the text can be aligned using TOP, CENTER, or BOTTOM. Valid
	 * values are:
	 * <UL>
	 * <LI><EM>{@link PositionConstants#CENTER}</EM>
	 * <LI>{@link PositionConstants#TOP}
	 * <LI>{@link PositionConstants#BOTTOM}
	 * <LI>{@link PositionConstants#LEFT}
	 * <LI>{@link PositionConstants#RIGHT}
	 * </UL>
	 * 
	 * @see #setLabelAlignment(int)
	 * @param align
	 *            the text alignment
	 * @since 2.0
	 */
	public void setTextAlignment(int align) {
		if (textAlignment == align)
			return;
		textAlignment = align;
		clearLocations();
		repaint();
	}

	/**
	 * Sets the placement of text relative to the label's icon. Valid values
	 * are:
	 * <UL>
	 * <LI><EM>{@link PositionConstants#EAST}</EM>
	 * <LI>{@link PositionConstants#NORTH}
	 * <LI>{@link PositionConstants#SOUTH}
	 * <LI>{@link PositionConstants#WEST}
	 * </UL>
	 * 
	 * @param where
	 *            the text placement
	 * @since 2.0
	 */
	public void setTextPlacement(int where) {
		if (textPlacement == where)
			return;
		textPlacement = where;
		revalidate();
		repaint();
	}

	private Rectangle getSelectionRectangle() {
		Rectangle bounds = getTextBounds();
		bounds.expand(new Insets(2, 2, 0, 0));
		translateToParent(bounds);
		bounds.intersect(getBounds());
		return bounds;
	}

	/**
	 * Calculates and returns the size of the Label's text. Note that this
	 * Dimension is calculated using the Label's full text, regardless of
	 * whether or not its text is currently truncated. If text size considering
	 * current truncation is desired, use {@link #calculateSubStringTextSize()}.
	 * 
	 * @return the size of the label's text, ignoring truncation
	 * @since 2.0
	 */
	protected Dimension calculateTextSize(int width, int height) {
		return FigureUtilities.getTextExtents(getWrappedText(width, height),
				getFont());
	}

	// Code for the Text Wrap.
	// Starts here.

	private String getWrappedText(int width, int height) {
		if (!isTextWrapped() || width == -1)
			return getText();

		if (isIconExist()) {
			switch (textPlacement) {
			case EAST:
			case WEST:
				width -= iconSize.width + getIconTextGap();
				break;
			case NORTH:
			case SOUTH:
				if (height != -1)
					height -= iconSize.height + getIconTextGap();
				break;
			}
		}

		Font f = getFont();
		int maxLines = Integer.MAX_VALUE;
		if (height != -1) {
			int fontHeight = FigureUtilities.getFontMetrics(f).getHeight();
			maxLines = (int) (height / (double) fontHeight);
		}

		StringBuffer accumlatedText = new StringBuffer();
		StringBuffer remainingText = new StringBuffer(getText());
		int i = 0, j = 0;

		while (remainingText.length() > 0 && j++ < maxLines) {
			if ((i = getLineWrapPosition(remainingText.toString(), f, height)) == 0)
				break;

			if (accumlatedText.length() > 0)
				accumlatedText.append("\n"); //$NON-NLS-1$
			accumlatedText.append(remainingText.substring(0, i));
			remainingText.delete(0, i);
		}
		return accumlatedText.toString();
	}

	private boolean isIconExist() {
		return true;
	}

	private int getLineWrapPosition(String string, Font f, int width) {
		BreakIterator iter = BreakIterator.getLineInstance();
		iter.setText(string);
		int start = iter.first();
		int end = iter.next();

		// if the first line segment does not fit in the width,
		// determine the position within it where we need to cut
		if (FigureUtilities.getTextExtents(string.substring(start, end), f).width > width) {
			iter = BreakIterator.getCharacterInstance();
			iter.setText(string);
			start = iter.first();
		}

		// keep iterating as long as width permits
		do
			end = iter.next();
		while (end != BreakIterator.DONE
				&& FigureUtilities.getTextExtents(string.substring(start, end),
						f).width <= width);
		return (end == BreakIterator.DONE) ? iter.last() : iter.previous();
	}

	/**
	 * Returns the largest substring of <i>s</i> in Font <i>f</i> that can be
	 * confined to the number of pixels in <i>availableWidth<i>.
	 * 
	 * @param s
	 *            the original string
	 * @param f
	 *            the font
	 * @param availableWidth
	 *            the available width
	 * @return the largest substring that fits in the given width
	 * @since 2.0 Copied from <class>FigureUtilities<class> Class, to avoid
	 *        call of static.
	 */
	static int getLargestSubstringConfinedTo(String s, Font f,
			int availableWidth) {
		FontMetrics metrics = FigureUtilities.getFontMetrics(f);
		int min, max;
		float avg = metrics.getAverageCharWidth();
		min = 0;
		max = s.length() + 1;

		// The size of the current guess
		int guess = 0, guessSize = 0;
		while ((max - min) > 1) {
			// Pick a new guess size
			// New guess is the last guess plus the missing width in pixels
			// divided by the average character size in pixels
			guess = guess + (int) ((availableWidth - guessSize) / avg);

			if (guess >= max)
				guess = max - 1;
			if (guess <= min)
				guess = min + 1;

			// Measure the current guess
			guessSize = FigureUtilities
					.getTextExtents(s.substring(0, guess), f).width;

			if (guessSize < availableWidth)
				// We did not use the available width
				min = guess;
			else
				// We exceeded the available width
				max = guess;
		}
		return min;
	}

	/**
	 * Sets to wrap text in Label.
	 * 
	 * @param b
	 *            true/false
	 */
	public void setTextWrap(boolean b) {
		if (isTextWrapped() == b)
			return;
		setFlag(FLAG_WRAP, b);
		revalidate();
		repaint();
	}

	/**
	 * @return text wrap.
	 */
	public boolean isTextWrapped() {
		return true;
	}

	/**
	 * Set text wrap width.
	 * 
	 * @param i
	 *            The label text wrap width
	 */
	public void setTextWrapWidth(int i) {
		if (this.wrapWidth == i)
			return;
		this.wrapWidth = i;
		revalidate();
		repaint();

	}

	/**
	 * Text Wrap Alignment, use only after using <method>setTextWrap(boolean)</method>
	 * 
	 * @param i
	 *            The label text wrapping width
	 */
	public void setTextWrapAlignment(int i) {
		if (getTextWrapAlignment() == i)
			return;

		setAlignmentFlags(i, FLAG_WRAP_ALIGN);
		repaint();
	}

	/**
	 * setAlignmentFlags
	 * 
	 * @param int
	 *            align
	 * @param int
	 *            flagOffset
	 * 
	 * --- TODO - copied needs to modify
	 */
	private void setAlignmentFlags(int align, int flagOffset) {
		flags &= ~(0x7 * flagOffset);
		switch (align) {
		case CENTER:
			flags |= 0x1 * flagOffset;
			break;
		case TOP:
			flags |= 0x2 * flagOffset;
			break;
		case LEFT:
			flags |= 0x3 * flagOffset;
			break;
		case RIGHT:
			flags |= 0x4 * flagOffset;
			break;
		case BOTTOM:
			flags |= 0x5 * flagOffset;
			break;
		}
	}

	/**
	 * 
	 * @param wrapValue
	 * @return PositionConstant representing the alignment
	 */
	private int getAlignment(int flagOffset) {
		int wrapValue = flags & (0x7 * flagOffset);
		if (wrapValue == 0x1 * flagOffset)
			return CENTER;
		else if (wrapValue == 0x2 * flagOffset)
			return TOP;
		else if (wrapValue == 0x3 * flagOffset)
			return LEFT;
		else if (wrapValue == 0x4 * flagOffset)
			return RIGHT;
		else if (wrapValue == 0x5 * flagOffset)
			return BOTTOM;

		return CENTER;
	}

	/**
	 * @return the label text wrapping width
	 */
	public int getTextWrapAlignment() {
		return getAlignment(FLAG_WRAP_ALIGN);
	}

	/**
	 * @return the focus state of this label
	 */
	public boolean hasFocus() {
		return (flags & FLAG_HASFOCUS) != 0;
	}

	/**
	 * @return the selection state of this label
	 */
	public boolean isSelected() {
		return (flags & FLAG_SELECTED) != 0;
	}

	private void graphicsdrawText(Graphics graphics) {
		String subString = getSubStringText();
		StringTokenizer tokenizer = new StringTokenizer(subString, "\n"); //$NON-NLS-1$

		Font f = getFont();
		int fontHeight = FigureUtilities.getFontMetrics(f).getHeight();
		int textWidth = FigureUtilities.getTextExtents(subString, f).width;
		int y = getTextLocation().y;

		// If the font's leading area is 0 then we need to add an offset to
		// avoid truncating at the top (e.g. Korean fonts)
		if (0 == FigureUtilities.getFontMetrics(f).getLeading()) {
			int offset = 2; // 2 is the leading area for default English
			y += offset;
		}

		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			System.out.println("Text added to draw" + token); //$NON-NLS-1$
			int tokenWidth = FigureUtilities.getTextExtents(token, f).width;
			int x = getTextLocation().x;
			switch (getTextWrapAlignment()) {
			case CENTER:
				x += (textWidth - tokenWidth) / 2;
				break;
			case RIGHT:
				x += textWidth - tokenWidth;
				break;
			}
			graphics.drawText(token, x, y);
			y += fontHeight;
		}
	}

	/**
	 * Sets the focus state of this label
	 * 
	 * @param b
	 *            true will cause a focus rectangle to be drawn around the text
	 *            of the Label
	 */
	public void setFocus(boolean b) {
		if (hasFocus() == b)
			return;
		setFlag(FLAG_HASFOCUS, b);
		repaint();
	}

	/**
	 * Sets the selection state of this label
	 * 
	 * @param b
	 *            true will cause the label to appear selected
	 */
	public void setSelected(boolean b) {
		if (isSelected() == b)
			return;
		setFlag(FLAG_SELECTED, b);
		repaint();
	}

}
