
CREATE TABLE IF NOT EXISTS account
(
    id       INTEGER PRIMARY KEY,
    username TEXT NOT NULL,
    password TEXT NOT NULL,
    name     TEXT DEFAULT NULL,
    token    TEXT NULL,
    CONSTRAINT unique_username UNIQUE (username)
);

CREATE TABLE IF NOT EXISTS post
(
    id           INTEGER PRIMARY KEY,
    author_id    INTEGER,
    title        TEXT NOT NULL,
    content      TEXT NOT NULL,
    published_at TIMESTAMP DEFAULT now(),
    FOREIGN KEY (author_id) REFERENCES account (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS post_comment
(
    id           INTEGER PRIMARY KEY,
    author_id    INTEGER,
    post_id      INTEGER,
    content      TEXT NOT NULL,
    published_at TIMESTAMP DEFAULT now(),
    CONSTRAINT comment_author_fk FOREIGN KEY (author_id) REFERENCES account (id) ON DELETE CASCADE,
    CONSTRAINT comment_post_fk FOREIGN KEY (post_id) REFERENCES post (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS ingredient
(
    id   INTEGER PRIMARY KEY,
    name TEXT NOT NULL,
    CONSTRAINT ingredient_unique_name UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS dish
(
    id          INTEGER PRIMARY KEY,
    name        TEXT NOT NULL,
    description TEXT    DEFAULT NULL,
    is_vegan    BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS recipe
(
    id           INTEGER PRIMARY KEY,
    author_id    INTEGER,
    dish_id      INTEGER,
    title        TEXT      NOT NULL,
    content      TEXT      NOT NULL,
    published_at TIMESTAMP NOT NULL DEFAULT now(),
    CONSTRAINT recipe_dish_fk FOREIGN KEY (dish_id) REFERENCES dish (id) ON DELETE CASCADE,
    CONSTRAINT recipe_author_fk FOREIGN KEY (author_id) REFERENCES account (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS ingredient_recipe
(
    ingredient_id INTEGER,
    recipe_id     INTEGER,
    CONSTRAINT ingredient_fk FOREIGN KEY (ingredient_id) REFERENCES ingredient (id) ON DELETE CASCADE,
    CONSTRAINT recipe_fk FOREIGN KEY (recipe_id) REFERENCES recipe (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS recipe_comment
(
    id           INTEGER PRIMARY KEY,
    author_id    INTEGER,
    recipe_id    INTEGER,
    content      TEXT NOT NULL,
    published_at TIMESTAMP DEFAULT now(),
    CONSTRAINT comment_author_fk FOREIGN KEY (author_id) REFERENCES account (id) ON DELETE CASCADE,
    CONSTRAINT comment_recipe_fk FOREIGN KEY (recipe_id) REFERENCES recipe (id) ON DELETE CASCADE
);
