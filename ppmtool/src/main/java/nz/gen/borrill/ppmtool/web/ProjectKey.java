package nz.gen.borrill.ppmtool.web;

class ProjectKey {
	
	private String value;
	
    ProjectKey() {}
	

	public ProjectKey(String value) {
		setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value.toUpperCase();
	}

	@Override
	public String toString() {
		return this.value;
	}
	
}
