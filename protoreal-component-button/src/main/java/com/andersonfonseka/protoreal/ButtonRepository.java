package com.andersonfonseka.protoreal;


import java.util.List;

import org.jdbi.v3.core.Jdbi;

import com.andersonfonseka.IComponent;
import com.andersonfonseka.IPage;
import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.dao.PageRepository;
import com.andersonfonseka.dao.Repository;
import com.andersonfonseka.dao.RepositoryImpl;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ButtonRepository extends RepositoryImpl implements Repository<IButton> {
	
	private static Jdbi handle;	
	
	public ButtonRepository() {}
	
	public void add(IButton button) {
			
		handle = DbConnection.getInstance(getMode()).getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("INSERT INTO BUTTON (UUID, CSSCLASS, OPENTYPE, ALIGNMENT, PAGEUUID, LABEL, SITEUUID, PAGERELATEDUUID) VALUES (?,?,?,?,?,?,?,?)") 
							.bind(0, button.getUuid())
							.bind(1, button.getCssClass())
							.bind(2, button.getOpenType())
							.bind(3, button.getAlignment())
							.bind(4, button.getPageUuid())
							.bind(5, button.getLabel())
							.bind(6, button.getSiteUuid())
							.bind(7, button.getPageRelatedUuid())
						.execute();
			});
	}
	
	public void remove(IButton button) {
		remove(getMode(), button.getUuid(), "DELETE FROM BUTTON WHERE UUID=?");
	}
	
	public IButton get(String uuid) {
		
		PageRepository pageRepository = new PageRepository();
		
		IButton button = (IButton) get(getMode(), uuid, "SELECT * FROM BUTTON WHERE UUID=?", Button.class);
		
		List<IPage> pages = pageRepository.list(button.getSiteUuid());
		button.setPages(pages);

		if (button.getPageRelatedUuid() != null) {
			if (button.getPageRelatedUuid().equals(button.getPageUuid())) {
				button.setPage(pageRepository.get(button.getPageRelatedUuid()));	
			} else {
				button.setPage(pageRepository.getFull(button.getPageRelatedUuid()));
			}
		}
		
		return button;
		
	}

	public void edit(IButton button) {
		
		handle = DbConnection.getInstance(getMode()).getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("UPDATE BUTTON SET CSSCLASS=?, OPENTYPE=?, ALIGNMENT=?, PAGEUUID=?, LABEL=?, SITEUUID=?, PAGERELATEDUUID=? WHERE UUID=?") 
							.bind(7, button.getUuid())
							.bind(0, button.getCssClass())
							.bind(1, button.getOpenType())
							.bind(2, button.getAlignment())
							.bind(3, button.getPageUuid())
							.bind(4, button.getLabel())
							.bind(5, button.getSiteUuid())
							.bind(6, button.getPageRelatedUuid())
						.execute();
			});
	}
	
	public void setMode(String mode) {
		
		super.setMode(mode);
		
		if (this.getMode().equals(DbConnection.TEST_MODE)) {
			
			handle = DbConnection.getInstance(getMode()).getHandle();
			
			handle.useHandle(handle -> {
				handle.execute("CREATE TABLE IF NOT EXISTS BUTTON (UUID VARCHAR, CSSCLASS VARCHAR, OPENTYPE VARCHAR, ALIGNMENT VARCHAR, PAGEUUID VARCHAR, LABEL VARCHAR, SITEUUID VARCHAR, PAGERELATEDUUID VARCHAR)");
			});
		}
	}


	public List<IComponent> list(String uuid) {
		return null;
	}
	
}
