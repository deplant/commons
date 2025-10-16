package tech.deplant.commons.io;

public class SystemContext {

	public static final CpuArch         PROCESSOR;
	public static final OperatingSystem OS;

	static {
		String cpu = System.getProperty("os.arch").toLowerCase();
		if (cpu.contains("amd64") || cpu.contains("x86_64")) {
			PROCESSOR = CpuArch.X86_64;
		} else if (cpu.contains("aarch64")) {
			PROCESSOR = CpuArch.ARM_64;
		} else {
			PROCESSOR = CpuArch.UNKNOWN;
		}
		String operSys = System.getProperty("os.name").toLowerCase();
		if (operSys.contains("win")) {
			OS = OperatingSystem.WINDOWS;
		} else if (operSys.contains("nix") || operSys.contains("nux")
		           || operSys.contains("aix")) {
			OS = OperatingSystem.LINUX;
		} else if (operSys.contains("mac")) {
			OS = OperatingSystem.MAC;
		} else if (operSys.contains("sunos")) {
			OS = OperatingSystem.SOLARIS;
		} else {
			OS = OperatingSystem.UNKNOWN;
		}
	}

	public enum CpuArch {
		X86_64,
		ARM_64,
		UNKNOWN
	}

	public enum OperatingSystem {
		WINDOWS,
		LINUX,
		MAC,
		SOLARIS,
		UNKNOWN
	}

}
