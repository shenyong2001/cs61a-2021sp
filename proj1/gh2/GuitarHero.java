package gh2;

import deque.ArrayDeque;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    public static final double CONCERT_A = 440.0;
    public static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    public static ArrayDeque<GuitarString> guitarStrings;

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        guitarStrings = new ArrayDeque<>();
        for (int i = 0; i < keyboard.length(); i++) {
            double CONCERT = CONCERT_A * Math.pow(2, (i - 24) / 12.0);
            GuitarString string = new GuitarString(CONCERT);
            guitarStrings.addLast(string);
        }
        System.out.println("");
        while (true) {
            GuitarString string;
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (0 <= index && index < keyboard.length()) {
                    string = guitarStrings.get(index);
                    string.pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = 0.0;
            for (int i = 0; i < guitarStrings.size(); i++) {
                sample += guitarStrings.get(i).sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (int i = 0; i < guitarStrings.size(); i++) {
                guitarStrings.get(i).tic();
            }
        }
    }
}
