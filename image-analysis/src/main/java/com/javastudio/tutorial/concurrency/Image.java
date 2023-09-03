package com.javastudio.tutorial.concurrency;

public class Image {

    public static final int ALPHA_FULLY_TRANSPARENT = 0x00;
    public static final int ALPHA_SEMI_TRANSPARENT = 0x80; // 128
    public static final int ALPHA_TRANSLUCENT = 0x80; // 128
    public static final int ALPHA_NEARLY_OPAQUE = 0xFE; // 254
    public static final int ALPHA_FULLY_OPAQUE = 0xFF;
    public static final int RED = 0x00FF0000;
    public static final int GREEN = 0x0000FF00;
    public static final int BLUE = 0x000000FF;
    private final int rgb;

    public Image(int red, int green, int blue) {
        this.rgb = createRGBFromColors(red, green, blue);
    }

    public static int createRGBFromColors(int red, int green, int blue) {
        int rgb = 0;

        rgb |= blue;
        rgb |= green << 8;
        rgb |= red << 16;

        rgb |= ALPHA_FULLY_OPAQUE << 24;

        return rgb;
    }

    public static boolean isShadeOfGray(int red, int green, int blue) {
        return Math.abs(red - green) < 30 &&
                Math.abs(red - blue) < 30 &&
                Math.abs(green - blue) < 30;
    }

    public static int getRed(int rgb) {
        return (rgb & RED) >> 16;
    }

    public static int getGreen(int rgb) {
        return (rgb & GREEN) >> 8;
    }

    public static int getBlue(int rgb) {
        return rgb & BLUE;
    }
}
