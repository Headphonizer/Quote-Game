package ui;

import java.awt.*;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class FuturisticFonts {
    private static final Map<String, Font> fontCache = new HashMap<>();

    public static Font loadFont(String path, float size) {
        String key = path + "_" + size;
        return fontCache.computeIfAbsent(key, k -> {
            try (InputStream is = FuturisticFonts.class.getResourceAsStream(path)) {
                if (is == null) {
                    throw new RuntimeException("Font not found: " + path);
                }   
                Font font = Font.createFont(Font.TRUETYPE_FONT, is);
                return font.deriveFont(size);
            } catch (Exception e) {
                e.printStackTrace();
                return new Font("SansSerif", Font.PLAIN, (int) size);
            }
        });
    }
}
