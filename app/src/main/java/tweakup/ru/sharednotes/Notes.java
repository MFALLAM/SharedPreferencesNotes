package tweakup.ru.sharednotes;

public class Notes {
    /**
     * The title of the note.
     * This private String variable stores the title or heading of a note instance.
     * It is part of the {@link Notes} class, which represents a simple note or memo.
     * The title provides a brief description of the content of the note.
     *
     * Access to this variable is restricted to methods within the {@link Notes} class,
     * and it is typically accessed and modified using getter and setter methods.
     *
     * @see Notes
     */
    private String title;

    /**
     * The content or body of the note.
     * This private String variable stores the detailed content of a note instance.
     * It is part of the {@link Notes} class, which represents a simple note or memo.
     * The note contains the main information or message conveyed by the note.
     *
     * Access to this variable is restricted to methods within the {@link Notes} class,
     * and it is typically accessed and modified using getter and setter methods.
     *
     * @see Notes
     */
    private String note;

    /**
     * Default constructor for the Notes class.
     * This constructor creates a new instance of the {@link Notes} class with
     * default values for the title and note. It is typically used when initializing
     * a note without providing specific title and content values.
     *
     * Note: This constructor may be useful for creating empty or default notes,
     * but it's often recommended to use the parameterized constructor
     * {@link #Notes(String, String)} for creating notes with specific content.
     *
     * @see Notes
     */
    public Notes() {
    }

    /**
     * Constructs a new Note with the specified title and content.
     *
     * This constructor creates a new instance of the {@link Notes} class,
     * initializing the title and content of the note with the provided parameters.
     *
     * @param title The title or heading of the note.
     * @param note  The detailed content or body of the note.
     * @see Notes
     */
    public Notes(String title, String note) {
        this.title = title;
        this.note = note;
    }

    /**
     * Retrieves the title of the note.
     * This method returns the title or heading of the note represented by the
     * current instance of the {@link Notes} class.
     *
     * @return The title of the note.
     * @see Notes
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the note.
     * This method updates the title or heading of the note represented by the
     * current instance of the {@link Notes} class with the specified value.
     *
     * @param title The new title to be set for the note.
     * @see Notes
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the content or body of the note.
     * This method returns the detailed content or body of the note represented by the
     * current instance of the {@link Notes} class.
     *
     * @return The content or body of the note.
     * @see Notes
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the content or body of the note.
     * This method updates the detailed content or body of the note represented by the
     * current instance of the {@link Notes} class with the specified value.
     *
     * @param note The new content or body to be set for the note.
     * @see Notes
     */
    public void setNote(String note) {
        this.note = note;
    }
}
