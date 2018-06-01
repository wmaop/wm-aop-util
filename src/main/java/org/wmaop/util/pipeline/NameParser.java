package org.wmaop.util.pipeline;

import com.webmethods.util.Pair;

public class NameParser {

	public static Pair<String, String> parseFQServiceName(String fqname) {
		
		String serviceName;
		String packageName;
		if (fqname == null) {
			serviceName = "";
			packageName = "";
		} else {
			int pkgsep = fqname.lastIndexOf(':');
			if (pkgsep == -1) {
				serviceName = fqname;
				packageName = "";
			} else {
				serviceName = fqname.substring(pkgsep + 1);
				packageName = fqname.substring(0, pkgsep);
			}
		}
		return new Pair<String, String>(packageName, serviceName);
	}
}
