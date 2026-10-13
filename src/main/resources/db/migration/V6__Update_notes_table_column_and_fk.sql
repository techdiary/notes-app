ALTER TABLE notebooks ALTER COLUMN description DROP NOT NULL;

ALTER TABLE notes ALTER COLUMN notebook_id DROP DEFAULT;
ALTER TABLE notes ALTER COLUMN notebook_id DROP NOT NULL;

UPDATE notes SET notebook_id = NULL
WHERE notebook_id IS NOT NULL
  AND NOT EXISTS (SELECT 1 FROM notebooks WHERE notebooks.id = notes.notebook_id);

ALTER TABLE notes
    ADD CONSTRAINT fk_notes_notebook
        FOREIGN KEY (notebook_id)
            REFERENCES notebooks(id);

CREATE INDEX idx_notes_notebook_id ON notes(notebook_id);