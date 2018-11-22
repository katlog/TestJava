package name.fw.effectivejava.examples.chapter06.item33;


// Using ordinal() to index array of arrays - DON'T DO THIS
public enum Phase0 {
	SOLID, LIQUID, GAS;

	public enum Transition {
		MELT, FREEZE, BOIL, CONDENSE, SUBLIME, DEPOSIT;

		// Rows indexed by src-ordinal,cols by dst-ordinal
		private static final Transition[][] TRANSITIONS= {
		    {null, MELT,SUBLIME},
		    {FREEZE,null,BOIL},
		    {DEPOSIT,CONDENSE,null}
		};

		
		public static Transition from(Phase0 src, Phase0 dst) {
			return TRANSITIONS[src.ordinal()][dst.ordinal()];
		}
	}

	// Simple demo program - prints a sloppy table
	public static void main(String[] args) {
		for (Phase0 src : Phase0.values())
			for (Phase0 dst : Phase0.values())
				if (src != dst)
					System.out.printf("%s to %s : %s %n", src, dst,
							Transition.from(src, dst));
	}
}
