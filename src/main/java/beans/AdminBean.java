package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.Admin;
import persistence.AdminDAO;


@ManagedBean
@SessionScoped
public class AdminBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Admin admin;
	
	private List<Admin> admins = new ArrayList<Admin>();
	
	
	public AdminBean(){
		admins = new AdminDAO().listarTodos();
		admin = new Admin();
		
	}
	
	public String salvar(){
		new AdminDAO().salvar(admin);
		admins = new AdminDAO().listarTodos();
		admin = new Admin();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario cadastrado com sucesso!"));
		return "lista-admin?faces-redirect=true";
	}
	

	public String editar(Admin admin){
		this.admin = admin;
		
		return "cadastro-admin?faces-redirect=true";
	}
	
	
	public void deletar(Admin admin){
		new AdminDAO().deletar(admin);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario deletado com sucesso!"));
		admins = new AdminDAO().listarTodos();
		
	}
	

	public String verificarUsuario() throws Exception {
		AdminDAO usuDAO = new AdminDAO();
		Admin us;
		String resultado;

		try {
			us = usuDAO.verificarDados(this.admin);

			if (us != null) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("admin", us);
			
				System.out.println("to no metodo");
				resultado = "index_admin?faces-redirect=true";
				
			} else {
				resultado = "Error";
				System.out.println("entrei no error");
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario n�o existe"));
			}

		} catch (Exception e) {
			throw e;
		}
		
		return resultado;
		
	}

	// verificar se o usuario esta logado
	public boolean verificarSessao() {
		boolean estado;

		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("admin") == null) {
			estado = false;
		} else {
			estado = true;
		}
		return estado;
	}

	// logout
	public String encerrarSessao() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		System.out.println("usei metodo");
		return "index?faces-redirect=true";
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public List<Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(List<Admin> admins) {
		this.admins = admins;
	}
	
	

}
