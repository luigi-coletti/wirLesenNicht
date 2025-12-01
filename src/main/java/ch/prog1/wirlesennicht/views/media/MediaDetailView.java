package ch.prog1.wirlesennicht.views.media;

import ch.prog1.wirlesennicht.data.Media;
import ch.prog1.wirlesennicht.services.Controller;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Optional;

@Route("media/:mediaId")
@PageTitle("Media Details")
public class MediaDetailView extends VerticalLayout implements BeforeEnterObserver {

    private final Controller controller;
    private Media media;

    public MediaDetailView(Controller controller) {
        this.controller = controller;
        setPadding(true);
        setSpacing(true);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<String> mediaId = event.getRouteParameters().get("mediaId");

        if (mediaId.isEmpty()) {
            event.forwardTo(""); // zurück zur Hauptseite
            return;
        }

        Media media = controller.getMediaById(mediaId.get());
        if (media == null) {
            add(new Text("Medium nicht gefunden"));
            return;
        }

        this.media = media;

        buildLayout();
    }

    private void buildLayout() {
        removeAll();

        add(new H2(media.getTitle()));
        add(new Paragraph(media.getDescription()));
        add(new Paragraph("Ersteller: " + media.getCreator()));
        add(new Paragraph("Ausgeliehen bis: " + media.getReturnDate()));

        Button borrowBtn = new Button("Ausleihen", click -> openBorrowDialog());
        add(borrowBtn);
    }

    private void openBorrowDialog() {
        Dialog dialog = new Dialog();

        TextField fname = new TextField("Vorname");
        TextField lname = new TextField("Nachname");

        FormLayout form = new FormLayout(fname, lname);

        Button confirm = new Button("Bestätigen", e -> {
            controller.lendMedia(media, fname.getValue(), lname.getValue());
            dialog.close();
        });

        Button cancel = new Button("Abbrechen", e -> dialog.close());

        dialog.add(new H2("Medium ausleihen"), form, confirm, cancel);
        dialog.open();
    }
}

