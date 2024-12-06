INSERT INTO utilisateur (nom, password) VALUES
("chef","$2a$10$31nhEmGLow2iIug.qqq6RuG3GXv1fo6wXfojXNswxqYqwR8kUJUEm"),
("rédacteur", "$2a$10$31nhEmGLow2iIug.qqq6RuG3GXv1fo6wXfojXNswxqYqwR8kUJUEm");

INSERT INTO priorite (status) VALUES
("Haute priorité"),
("Priorité moyenne"),
("Basse priorité");

INSERT INTO tache (titre, description, valide) VALUES
("Avancement projet", "Donec porta diam nec neque lacinia porta. Curabitur porttitor tristique congue.", 0),
("Projet fil rouge", "Donec porta diam nec neque lacinia porta. Curabitur porttitor tristique congue.", 1);

INSERT INTO droit (nom) VALUES
("employé"),
("rédacteur"),
("adminstrateur");