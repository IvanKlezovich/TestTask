INSERT INTO users (id, name, date_of_birth, password)
VALUES (1, 'Алексей Николаевич', '1978-03-15', 'Kj9mN7pQ2rS'),
       (2, 'Екатерина Сергеевна', '1992-08-22', 'LpM8nO9qR1t'),
       (3, 'Михаил Андреевич', '1987-02-14', 'VbC4xZ5wD6e'),
       (4, 'Мария Ивановна', '1995-11-30', 'HgF7jKlM9n'),
       (5, 'Сергей Викторович', '1982-06-28', 'RsTqP3oN4m'),
       (6, 'Наталья Михайловна', '1991-09-18', 'YcX8zW9vB7'),
       (7, 'Дмитрий Петрович', '1985-04-25', 'EwQ2rT5yU7'),
       (8, 'Анна Алексеевна', '1993-01-12', 'TnM9kL8pQ7'),
       (9, 'Иван Васильевич', '1989-07-20', 'RsEwQ2rT5'),
       (10, 'Ольга Николаевна', '1994-03-15', 'YzWxV9uB8n');
INSERT INTO ACCOUNT (ID, USER_ID, BALANCE, START_DEPOSIT)
VALUES (1, 1, 1000.00, 1000.00),
       (2, 2, 2000.00, 2000.00),
       (3, 3, 1500.00, 1500.00),
       (4, 4, 2500.00, 2500.00),
       (5, 5, 3000.00, 3000.00),
       (6, 6, 1200.00, 1200.00),
       (7, 7, 1800.00, 1800.00),
       (8, 8, 2200.00, 2200.00),
       (9, 9, 2800.00, 2800.00),
       (10, 10, 3500.00, 3500.00);
INSERT INTO EMAIL_DATA (ID, USER_ID, EMAIL)
VALUES (1, 1, 'alexey.petrov@company.com'),
       (2, 2, 'ekaterina.sergeevna@company.com'),
       (3, 3, 'mikhail.andreevich@company.com'),
       (4, 4, 'maria.ivanovna@company.com'),
       (5, 5, 'sergey.viktorovich@company.com'),
       (6, 6, 'natalya.mikhailovna@company.com'),
       (7, 7, 'dmitry.petrovich@company.com'),
       (8, 8, 'anna.alexeevna@company.com'),
       (9, 9, 'ivan.vasilevich@company.com'),
       (10, 10, 'olga.nikolaevna@company.com');
INSERT INTO PHONE_DATA (ID, USER_ID, PHONE)
VALUES (1, 1, '+79991234567'),
       (2, 2, '+79991234568'),
       (3, 3, '+79991234569'),
       (4, 4, '+79991234570'),
       (5, 5, '+79991234571'),
       (6, 6, '+79991234572'),
       (7, 7, '+79991234573'),
       (8, 8, '+79991234574'),
       (9, 9, '+79991234575'),
       (10, 10, '+79991234576');
