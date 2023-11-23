package tweakup.ru.sharednotes;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * A tag used for logging purposes in the MainActivity class.
     * This private static final String variable serves as a tag for logging statements
     * specific to the MainActivity class. It is often used in conjunction with Android's
     * logging system to identify log messages originating from this class.
     * Using the class's simple name as the tag provides a concise identifier
     * when logging information related to the MainActivity class.
     */
    private static final String TAG = MainActivity.class.getSimpleName();

    /**
     * The name used for storing preferences related to notes.
     * This private static final String represents the name of the preferences file
     * used to store and retrieve data related to notes, such as note count.
     */
    private static final String PREFS_NAME = "NotePrefs";

    /**
     * The key used to store and retrieve the count of notes in preferences.
     * This private static final String represents the key used in the preferences file
     * to store and retrieve the count of notes. It is associated with the {@link #PREFS_NAME}.
     */
    private static final String KEY_NOTE_COUNT = "NoteCount";

    /**
     * The container for displaying notes in the user interface.
     * This private LinearLayout variable represents the container in the user interface
     * where notes are displayed or organized.
     */
    private LinearLayout notesContainer;

    /**
     * The list containing instances of the Notes class.
     * This private List variable stores a collection of {@link Notes} instances,
     * representing the notes that are managed or displayed in the application.
     */
    private List<Notes> notesList;

    /**
     * The button used to save or update notes.
     * This private Button variable represents the user interface element
     * that is used to trigger the saving or updating of notes.
     * It is typically associated with an action that persists changes made to notes.
     */
    private Button btnSave;

    /**
     * The EditText widget for entering or displaying note titles.
     * This private EditText variable represents a user interface element
     * that allows users to input or view titles for notes. It is typically used
     * in conjunction with the {@link Notes} class to manage the title of a note.
     */
    private EditText titleEditText;

    /**
     * The EditText widget for entering or displaying note content.
     * This private EditText variable represents a user interface element
     * that allows users to input or view the detailed content of a note.
     * It is typically used in conjunction with the {@link Notes} class to manage
     * the content or body of a note.
     */
    private EditText noteEditText;


    /**
     * A reference to the SharedPreferences object.
     * <p>
     * This variable represents an instance of the SharedPreferences class,
     * which is used to store and retrieve key-value pairs persistently across
     * application sessions
     */
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notesList = new ArrayList<>();

        btnSave = findViewById(R.id.btn_save);

        notesContainer = findViewById(R.id.notes_container);

        btnSave.setOnClickListener(view -> {
            saveNote();
            // TODO DELETE this line later
            for (int i = 0; i < notesList.size(); i++) {
                Log.d(TAG, "Counter --> " + i);

                Log.d(TAG, "Title -> " + notesList.get(i).getTitle());
                Log.d(TAG, "Body -> " + notesList.get(i).getNote());
            }
        });
        displayNotes();
        loadNotesFromSharedPreferences();
    }

    private void displayNotes() {
        for (Notes note : notesList) {
            createNoteView(note);
        }
    }

    /**
     * Loads notes from SharedPreferences and populates the notesList.
     * This private method retrieves stored note information from SharedPreferences,
     * including the count of notes and their individual details, and populates
     * the notesList with the loaded notes.
     *
     * The method performs the following steps:
     * 1. Retrieves the SharedPreferences instance using the specified PREFS_NAME and MODE_PRIVATE.
     * 2. Retrieves the count of notes stored in SharedPreferences.
     * 3. Iterates over each stored note, retrieving its title and note content.
     * 4. Creates new Notes instances and adds them to the notesList.
     *
     * Call this method when you want to load stored notes into the application.
     *
     * @see Notes
     */
    private void loadNotesFromSharedPreferences() {
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int noteCount = sharedPreferences.getInt(KEY_NOTE_COUNT, 0);

        for (int i = 0; i < noteCount; i++) {
            String title = sharedPreferences.getString(getString(R.string.NOTE_KEY_TITLE) + i, "");
            String body = sharedPreferences.getString(getString(R.string.NOTE_KEY_BODY) + i, "");

            Notes note = new Notes(title, body);
            notesList.add(note);
        }
    }

    /**
     * Saves a new note based on user input.
     * <p>
     * This private method is responsible for extracting user input from the title
     * and note EditText widgets in the user interface, creating a new {@link Notes}
     * instance, and adding it to the notesList if both the title and note content are non-empty.
     * <p>
     * The method performs the following steps:
     * 1. Retrieves references to the title and note EditText widgets using their IDs.
     * 2. Extracts the user-inputted title and note content from the EditText widgets.
     * 3. Checks if both the title and note content are non-empty.
     * 4. If non-empty, creates a new Notes instance, sets its title and note content,
     * and adds it to the notesList collection.
     * <p>
     * Note: It's important to call this method when the user intends to save a new note
     * based on the entered information.
     *
     * @see Notes
     */
    private void saveNote() {
        titleEditText = findViewById(R.id.edit_tile);
        noteEditText = findViewById(R.id.edit_message);

        String title = titleEditText.getText().toString();
        String body = noteEditText.getText().toString();

        if (!title.isEmpty() && !body.isEmpty()) {
            Notes note = new Notes();
            note.setTitle(title);
            note.setNote(body);

            notesList.add(note);
            saveNotesToSharedPreference();
            createNoteView(note);
            clearInputsEditTextViews();
        }
    }

    /**
     * Clears the input EditText widgets for title and note content.
     * <p>
     * This private method is designed to be called after saving a note,
     * resetting the input fields to an empty state for new note entry.
     */
    private void clearInputsEditTextViews() {
        titleEditText.setText("");
        noteEditText.setText("");
    }

    /**
     * Creates a view to display a single note.
     * <p>
     * This private method dynamically inflates a layout for a note using the LayoutInflater,
     * sets the title and note content based on the provided Notes object, and attaches
     * an OnClickListener to the created view to trigger the display of a delete dialog.
     * <p>
     * Note: It's important to call this method when dynamically creating views to display notes.
     *
     * @param note The Notes object containing information about the note to be displayed.
     * @see Notes
     */
    private void createNoteView(final Notes note) {
        View notesView = getLayoutInflater().inflate(R.layout.note_item, null);
        TextView titleTextView = notesView.findViewById(R.id.item_title_textview);
        TextView bodyTextView = notesView.findViewById(R.id.item_body_textview);

        titleTextView.setText(note.getTitle());
        bodyTextView.setText(note.getNote());

        notesView.setOnClickListener(view -> {
            showDeleteDialog();
        });
        notesContainer.addView(notesView);
    }

    private void showDeleteDialog() {
    }

    /**
     * Saves the current state of the notesList to SharedPreferences.
     * <p>
     * This private method retrieves the SharedPreferences instance using the
     * specified PREFS_NAME and MODE_PRIVATE, and then creates a SharedPreferences
     * Editor to store the count of notes and individual note details persistently.
     *
     * @see Notes
     */
    private void saveNotesToSharedPreference() {
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_NOTE_COUNT, notesList.size());

        for (int i = 0; i < notesList.size(); i++) {
            Notes note = notesList.get(i);
            editor.putString(getString(R.string.NOTE_KEY_TITLE) + i, note.getTitle());
            editor.putString(getString(R.string.NOTE_KEY_BODY) + i, note.getNote());
        }
        editor.apply();
    }

}