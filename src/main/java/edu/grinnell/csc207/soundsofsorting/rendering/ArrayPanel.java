package edu.grinnell.csc207.soundsofsorting.rendering;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import edu.grinnell.csc207.soundsofsorting.audio.NoteIndices;

/**
 * A drawing panel for visualizing the contents of a @NoteIndices object.
 */
public class ArrayPanel extends JPanel {
    private NoteIndices notes;

    /**
     * Create a new ArrayPanel with the given notes and dimensions.
     * 
     * @param notes  the note indices
     * @param width  the width of the panel
     * @param height the height of the panel
     */
    public ArrayPanel(NoteIndices notes, int width, int height) {
        this.notes = notes;
        this.setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Integer[] noteIndices = notes.getNotes();
        int width = getWidth();
        int height = getHeight();
        int barWidth = width / noteIndices.length;

        // Find the maximum value to scale the bars
        int maxValue = 0;
        for (int i = 0; i < noteIndices.length; i++) {
            maxValue = Math.max(maxValue, noteIndices[i]);
        }

        // Draw each bar
        for (int i = 0; i < noteIndices.length; i++) {
            int barHeight = (int) ((double) noteIndices[i] / maxValue * height);
            int x = i * barWidth;
            int y = height - barHeight;

            // Set color based on whether the index is highlighted
            if (notes.isHighlighted(i)) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLUE);
            }

            g.fillRect(x, y, barWidth - 1, barHeight);
        }
    }
}