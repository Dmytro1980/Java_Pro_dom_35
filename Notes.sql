CREATE TABLE IF NOT EXISTS public."Notes"
(
    "id"         SERIAL      NOT NULL,
    "name"       VARCHAR(30) NOT NULL,
    "content"    TEXT,
    "created_at" BIGINT      NOT NULL,
    CONSTRAINT notes_pkey PRIMARY KEY (id)
);

INSERT INTO public."Notes" ("name", "content", created_at)
VALUES ('первая заметка', 'текст первой заметки', 1682906699924),
       ('второая заметка', 'длинный текст второй заметки', 1683133899924),
       ('заметка', 'очень длинный текст заметки', 1683213899924),
       ('заметка гы', 'очень длинный текст заметки', 1683343899924),
       ('заметка', 'очень длинный текст заметки гы', 1683583899924),
       ('note', 'note long text', 1684059628718),
       ('long note', 'long note text long note text long note text', 1684020452844),
       ('very long note', 'long note text long note text long note text qwesqwsa', 1684085091828);

