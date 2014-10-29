package org.vaadin.mockapp.samples;

import org.vaadin.mockapp.MockAppUI;
import org.vaadin.mockapp.samples.charts.SampleChartView;
import org.vaadin.mockapp.samples.crud.SampleCrudView;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Content of the UI when the user is logged in.
 * 
 * 
 */
public class MainScreen extends VerticalLayout {


	public MainScreen(MockAppUI ui) {
		setSizeFull();
		final Panel viewContainer = new Panel();
		viewContainer.setSizeFull();
		viewContainer.addStyleName(Reindeer.PANEL_LIGHT);

		final Navigator navigator = new Navigator(ui, viewContainer);
		navigator.setErrorView(ErrorView.class);

		createHeader(navigator);

		addComponent(viewContainer);
		setExpandRatio(viewContainer, 1);
	}

	private void createHeader(Navigator navigator) {
		final HorizontalLayout header = new HorizontalLayout();
		header.setWidth("100%");
		header.setMargin(true);
		header.setSpacing(true);
		header.addStyleName("black");
		
		
		final Label headerLabel = new Label(FontAwesome.TABLE.getHtml() + " My CRUD", ContentMode.HTML);
		headerLabel.addStyleName(ValoTheme.LABEL_H3);
		headerLabel.setWidth(null);
		
		final HorizontalLayout viewLayout = createViewsAndNavigation(navigator);		
		
		Button logoutButton = new Button("Logout", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				VaadinSession.getCurrent().getSession().invalidate();
				Page.getCurrent().reload();
			}
		});
		logoutButton.setIcon(FontAwesome.SIGN_OUT);
		
		header.addComponent(headerLabel);
		header.addComponent(viewLayout);
		header.addComponent(logoutButton);
		header.setComponentAlignment(headerLabel, Alignment.MIDDLE_CENTER);
		header.setComponentAlignment(viewLayout, Alignment.MIDDLE_CENTER);
		header.setComponentAlignment(logoutButton, Alignment.MIDDLE_CENTER);
		header.setExpandRatio(viewLayout, 1);
		addComponent(header);
	}

	private HorizontalLayout createViewsAndNavigation(final Navigator navigator) {
		HorizontalLayout viewLayout = new HorizontalLayout();
		// Crud view. Use an instance to avoid reinitializing during edit
		// operations
		navigator.addView(SampleCrudView.VIEW_NAME, new SampleCrudView());
		Button crudButton = new Button(SampleCrudView.VIEW_NAME, new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				navigator.navigateTo(SampleCrudView.VIEW_NAME);
				
			}
		});
		crudButton.setIcon(FontAwesome.EDIT);
//		menuBar.addItem(SampleCrudView.VIEW_NAME, new NavigateCommand(
//				SampleCrudView.VIEW_NAME));

		// Charts view, reinitialize on every visit
		navigator.addView(SampleChartView.VIEW_NAME, new SampleCrudView());
		Button chartButton = new Button(SampleChartView.VIEW_NAME, new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				navigator.navigateTo(SampleChartView.VIEW_NAME);
				
			}
		});
		chartButton.setIcon(FontAwesome.INFO_CIRCLE);
//		navigator.addView(SampleChartView.VIEW_NAME, SampleChartView.class);
//		menuBar.addItem(SampleChartView.VIEW_NAME, new NavigateCommand(
//				SampleChartView.VIEW_NAME));

//		menuBar.addItem("Log out", new MenuBar.Command() {
//			@Override
//			public void menuSelected(MenuBar.MenuItem selectedItem) {
//				VaadinSession.getCurrent().getSession().invalidate();
//				Page.getCurrent().reload();
//			}
//		});
		viewLayout.addComponent(crudButton);
		viewLayout.addComponent(chartButton);
		return viewLayout;
	}

}
