package com.topicMe.managed.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.button.Button;
import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.component.panel.Panel;
import org.primefaces.component.panelgrid.PanelGrid;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import com.topicMe.businessdomain.dao.BoardDAO;
import com.topicMe.model.businessdomain.Board;


@ManagedBean(name = "dashboardBean")
@SessionScoped
public class BoardManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private DashboardModel model = null;
	private Dashboard dashBoard;
		
	
	@ManagedProperty(value="#{boardDAOImpl}")
	private
	BoardDAO boardDAO;
	
    private Board board = null;
    private String name;
	private String description;
	private boolean privateMode = true;
	private String boardCategory;
	

	public BoardManagedBean() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPrivateMode() {
		return privateMode;
	}

	public void setPrivateMode(boolean privateMode) {
		this.privateMode = privateMode;
	}

	public String getBoardCategory() {
		return boardCategory;
	}

	public void setBoardCategory(String boardCategory) {
		this.boardCategory = boardCategory;
	}
    
	public String createBoard() {  
		 board = new Board();
         board.setName(name);
         board.setDescription(description);
         board.setBoardCategory(boardCategory);
         getBoardDAO().persistBoard(board);
         
         addNewBoardToDashboard(board);
         
         System.out.println("\n\n ===> Board id  "+board.getId() +" Board Name "+name);
         
         clearBoard();
         return "topic";
    }
	
		
	public void clearBoard(){
		 name=null;
		 description = null;
		 boardCategory = null;
    }

	
	public BoardDAO getBoardDAO() {
		 return boardDAO;
	}

	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
	public DashboardModel getModel() {
		if (model == null){
			model = new DefaultDashboardModel();
			DashboardColumn column1 = new DefaultDashboardColumn();
			column1.addWidget(PageIdConstants.INDEXPAGE_CREATE_BOARD_PANEL_ID);
			model.addColumn(column1);
			
			List<Long> allBoardIds = getBoardDAO().getAllBoardIds();
			for(Long id : allBoardIds){
			
				DashboardColumn column = new DefaultDashboardColumn();
				column.addWidget(PageIdConstants.INDEXPAGE_CREATE_BOARD_PANEL_ID + id);
				model.addColumn(column);
				
			}
			
		}
        
		return model;
	}
	
  
	public void setDashBoard(Dashboard uiComponent){
		dashBoard = uiComponent;
	}
	
	public Dashboard getDashBoard(){
		if(dashBoard != null){
			System.out.println(" Number of childern in dashboard: "+dashBoard.getChildCount());
		}
	    return dashBoard;
	}
	
	private void addNewBoardToDashboard(Board board){
		
		if(dashBoard !=null){
			
			
			Panel panel = new Panel();
			panel.setId(PageIdConstants.INDEXPAGE_CREATE_BOARD_PANEL_ID+board.getId());
			panel.setHeader(board.getName());
			panel.setStyleClass("addBoardPanel");
			
			PanelGrid panelGrid = new PanelGrid();
			panelGrid.setColumns(1);
			panelGrid.setId(PageIdConstants.INDEXPAGE_BOARD_PANELGRID_ID + board.getId());
			
		    Button button = new Button();
		    button.setValue("Add New Topic");
		    button.setHref("/pages/board.faces");
				
			button.setId(PageIdConstants.INDEXPAGE_BOARD_PANELGRID_BUTTON_ID + board.getId());
			
			panelGrid.getChildren().add(button);
			
			panel.getChildren().add(panelGrid);
			
			dashBoard.getChildren().add(panel);
			
			DashboardColumn column = new DefaultDashboardColumn();
			column.addWidget(PageIdConstants.INDEXPAGE_CREATE_BOARD_PANEL_ID+ board.getId());
			model.addColumn(column);
			System.out.println(">>>> Add board id "+ board.getId() +" to dash board");
	
		}
	}
	
	
	public void handleReorder(DashboardReorderEvent event) {
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		message.setSummary("Reordered: " + event.getWidgetId());
		message.setDetail("Item index: " + event.getItemIndex() + ", Item Column index: " + event.getColumnIndex() + ", Sender Column index: " + event.getSenderColumnIndex());
		
		addMessage(message);
	}
	
	
	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	

}
			
