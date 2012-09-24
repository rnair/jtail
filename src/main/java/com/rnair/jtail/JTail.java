package com.rnair.jtail;

import com.rnair.jtail.config.JTailConfiguration;
import com.rnair.jtail.core.SimpleTailingController;
import com.rnair.jtail.core.TailingController;

public class JTail {

	public static void main(String[] args) {
		try {
			if (verifyCLI(args)) {
				
				JTailConfiguration config = JTailConfiguration.of(args);

				TailingController controller = new SimpleTailingController();
				controller.initialize(config);
				controller.start();

			}

		} catch (Exception e) {
			System.err.println("Best effort!" + e.getLocalizedMessage());
		}
	}

	public static boolean verifyCLI(String... args) {
		if (args.length == 0) {
			System.err.println("Could not find any args");
			return false;
		}
		return true;
	}
}
