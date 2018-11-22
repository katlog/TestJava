// Broken - violates symmetry! - Pages 36-37
package name.fw.effectivejava.examples.chapter03.item12;


public final class CaseInsensitiveString implements Comparable<CaseInsensitiveString>{
	private final String s;

	public CaseInsensitiveString(String s) {
		if (s == null)
			throw new NullPointerException();
		this.s = s;
	}

	// Broken - violates symmetry!
	@Override
	public boolean equals(Object o) {
		if (o instanceof CaseInsensitiveString)
			return s.equalsIgnoreCase(((CaseInsensitiveString)o).s);
		if (o instanceof String) // One-way interoperability!
			return s.equalsIgnoreCase((String) o);
		System.out.println(((CaseInsensitiveString)o).s);
		return false;
	}

    @Override
    public int compareTo(CaseInsensitiveString o) {
        return String.CASE_INSENSITIVE_ORDER.compare(s, o.s);
    }

}
