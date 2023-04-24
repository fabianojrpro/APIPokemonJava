
public class Pokemon {
    public String nome;
    public int id;
    public String imagem;
    public String[] tipos;
    public String[] habilidades;
    public int[] estatisticas;
    
    public Pokemon(String nome, int id, String[] tipos, String[] habilidades, int[] estatisticas) {
        this.nome = nome;
        this.id = id;
        this.tipos = tipos;
        this.habilidades = habilidades;
        this.estatisticas = estatisticas;
    }
    
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String[] getTipos() {
		return tipos;
	}
	public void setTipos(String[] tipos) {
		this.tipos = tipos;
	}
	public String[] getHabilidades() {
		return habilidades;
	}
	public void setHabilidades(String[] habilidades) {
		this.habilidades = habilidades;
	}
	public int[] getEstatisticas() {
		return estatisticas;
	}
	public void setEstatisticas(int[] estatisticas) {
		this.estatisticas = estatisticas;
	}

}
