package ch.prog1.wirlesennicht.views.masterdetail;

import ch.prog1.wirlesennicht.data.Media;
import ch.prog1.wirlesennicht.data.Person;
import ch.prog1.wirlesennicht.services.Controller;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.util.stream.Stream;

@PageTitle("Master-Detail")
@Route("/:samplePersonID?/:action?(edit)")
@Menu(order = 0, icon = LineAwesomeIconUrl.COLUMNS_SOLID)
@RouteAlias("")
@Uses(Icon.class)
public class MasterDetailView extends Div {

    private final String SAMPLEPERSON_ID = "samplePersonID";

    private Select<Person> personSelect = new Select<>();
    private HorizontalLayout filterBar = new HorizontalLayout();
    private VerticalLayout filterLayout = new VerticalLayout();
    private TextField searchField = new TextField("Titel suchen");
    private final Grid<Media> grid = new Grid<>(Media.class, false);
    private Grid.Column<Media> returnColumn;

    private final Controller controller;

    public MasterDetailView(Controller controller) {
        this.controller = controller;

        addClassNames("master-detail-view");

        // Create UI
        SplitLayout splitLayout = new SplitLayout();

        // Configure Grid
        configureGrid(controller);


        returnColumn = grid.addComponentColumn(media -> {
            Button returnBtn = new Button("Zurückgeben");
            returnBtn.addClickListener(e -> {
                controller.returnMedia(media);
                refreshGrid();
            });
            returnBtn.setEnabled(media.getLentDate() != null);
            return returnBtn;
        });

        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate("media/" + event.getValue().getId());
            } else {
                UI.getCurrent().navigate(MasterDetailView.class);
            }
        });

        configureFilterAndSort(controller);

        createLayout(splitLayout);

        add(splitLayout);
    }

    private void configureFilterAndSort(Controller controller) {
        personSelect.setLabel("Person");
        personSelect.setPlaceholder("Alle");
        personSelect.setEmptySelectionAllowed(true);
        personSelect.setItems(controller.getUsers());
        personSelect.setItemLabelGenerator(p -> p != null ? p.getFirstName() + " " + p.getLastName() : " ");
        personSelect.addValueChangeListener(e -> {

            Person selected = e.getValue();
            returnColumn.setVisible(selected != null);
            refreshGrid();
        });

        searchField.setPlaceholder("Titel...");
        searchField.setClearButtonVisible(true);
        searchField.setWidth("300px");
        searchField.setValueChangeMode(ValueChangeMode.LAZY);

        searchField.addValueChangeListener(e -> refreshGrid());
    }

    private void configureGrid(Controller controller) {
        grid.addColumn("id").setAutoWidth(true).setHeader("ID");
        grid.addColumn("title").setAutoWidth(true).setHeader("Title");
        grid.addColumn(Media::getDescription).setAutoWidth(true).setHeader("Beschreibung");
        grid.addColumn(Media::getLentDate).setAutoWidth(true).setHeader("Ausgeliehen bis");

        grid.setItems(
                query -> {

                    Person selected = personSelect.getValue();
                    String search = searchField.getValue() == null ? "" : searchField.getValue().toLowerCase();

                    // gleiche Logik wie zuvor – aber nun wird limitiert und geskippt
                    Stream<Media> baseStream;

                    if (selected != null && !search.isBlank()) {
                        baseStream = controller.getMediasByPerson(selected.getFirstName(), selected.getLastName())
                                .stream()
                                .filter(media -> media.getTitle().toLowerCase().contains(search));

                    } else if (selected != null) {
                        baseStream = controller.getMediasByPerson(selected.getFirstName(), selected.getLastName())
                                .stream();

                    } else if (!search.isBlank()) {
                        baseStream = controller.getAllMedias()
                                .stream()
                                .filter(media -> media.getTitle().toLowerCase().contains(search));

                    } else {
                        baseStream = controller.getAllMedias().stream();
                    }

                    return baseStream
                            .skip(query.getOffset())   // Wichtig!
                            .limit(query.getLimit());   // Wichtig!
                },

                // Count callback
                query -> {
                    Person selected = personSelect.getValue();
                    String search = searchField.getValue() == null ? "" : searchField.getValue().toLowerCase();

                    Stream<Media> baseStream;

                    if (selected != null && !search.isBlank()) {
                        baseStream = controller.getMediasByPerson(selected.getFirstName(), selected.getLastName())
                                .stream()
                                .filter(media -> media.getTitle().toLowerCase().contains(search));

                    } else if (selected != null) {
                        baseStream = controller.getMediasByPerson(selected.getFirstName(), selected.getLastName())
                                .stream();

                    } else if (!search.isBlank()) {
                        baseStream = controller.getAllMedias()
                                .stream()
                                .filter(media -> media.getTitle().toLowerCase().contains(search));

                    } else {
                        baseStream = controller.getAllMedias().stream();
                    }

                    return (int) baseStream.count();
                }
        );
    }


    private void createLayout(SplitLayout splitLayout) {
        filterBar = new HorizontalLayout();
        filterBar.setWidthFull();
        filterBar.setAlignItems(FlexComponent.Alignment.END);
        filterBar.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        filterBar.getStyle().set("margin-bottom", "10px");
        filterBar.add(personSelect,searchField);

        filterLayout = new VerticalLayout();
        filterLayout.setWidthFull();
        filterLayout.add(filterBar);
        filterLayout.add(grid);

        Div wrapper = new Div();
        wrapper.setClassName("grid-wrapper");
        wrapper.add(filterLayout);
        splitLayout.addToPrimary(wrapper);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

}
