package model;

public enum GrupoMuscular {
	
	P("Peitoral"), C("Costas"), B("Biceps");

	private String label;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	private GrupoMuscular(String label) {
		this.label = label;
	}
}

