package flappy.cz.uhk.smitkja1.gui;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kriz on 14. 12. 2016.
 */
public class LevelPicker {

    class LevelDescription {
        String title;
        String url;

        public LevelDescription(String title, String url) {
            this.title = title;
            this.url = url;
        }

        @Override
        public String toString() {
            return title;
        }
    }

    public String pickAndLoadLevel() {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new URL("http://edu.uhk.cz/~krizpa1/doku.php?id=flappy_bird_-_levely&do=export_raw").openStream()))) {

            List<LevelDescription> lines = new ArrayList<>();
            String line = null;
            Pattern p = Pattern.compile("^(.+) (.+?)$"); // rozdelit string podle posledni mezery
            while ((line = reader.readLine()) != null) {
                Matcher m = p.matcher(line);
                if (m.matches()) {
                    LevelDescription ld = new LevelDescription(m.group(1), m.group(2));
                    lines.add(ld);
                }
            }

            LevelDescription[] possibilities = lines.toArray(new LevelDescription[lines.size()]);
            LevelDescription s = (LevelDescription)JOptionPane.showInputDialog(
                    null,
                    "Vyberte level:",
                    "Vyber",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    possibilities,
                    null);

            return s.url;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
