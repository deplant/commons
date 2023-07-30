package tech.deplant.commons;

public class SystemContext {

	private static ProcessorArchitecture processor = null;
	private static OperatingSystem operatingSystem = null;

	public static ProcessorArchitecture PROCESSOR() {
		if (processor == null) {
			String operSys = System.getProperty("os.arch").toLowerCase();
			if (operSys.contains("amd64") || operSys.contains("x86_64")) {
				processor = ProcessorArchitecture.X86_64;
			} else if (operSys.contains("aarch64")) {
				processor = ProcessorArchitecture.ARM_64;
			}
		}
		return processor;
	}

	public static OperatingSystem OS() {
		if (operatingSystem == null) {
			String operSys = System.getProperty("os.name").toLowerCase();
			if (operSys.contains("win")) {
				operatingSystem = OperatingSystem.WINDOWS;
			} else if (operSys.contains("nix") || operSys.contains("nux")
			           || operSys.contains("aix")) {
				operatingSystem = OperatingSystem.LINUX;
			} else if (operSys.contains("mac")) {
				operatingSystem = OperatingSystem.MAC;
			} else if (operSys.contains("sunos")) {
				operatingSystem = OperatingSystem.SOLARIS;
			}
		}
		return operatingSystem;
	}

	public enum ProcessorArchitecture {
		X86_64,
		ARM_64
	}

	public enum OperatingSystem {
		WINDOWS,
		LINUX,
		MAC,
		SOLARIS
	}
}
