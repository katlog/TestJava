// Broken - violates symmetry! - Pages 36-37
package name.fw.effectivejava.examples.chapter03.item08;

import java.util.ArrayList;
import java.util.List;

public final class CaseInsensitiveString {
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

	// This version is correct.
//	 @Override public boolean equals(Object o) {
//    	 return o instanceof CaseInsensitiveString &&
//    	     ((CaseInsensitiveString) o).s.equalsIgnoreCase(s);
//	 }

	public static void main(String[] args) {
		CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
		String s = "polish";
		System.out.println(cis.equals(s) + "  " + s.equals(cis));
		
		List<CaseInsensitiveString> list = new ArrayList<CaseInsensitiveString>();
		list.add(cis);
	}
}
