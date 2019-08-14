-- 1) Who checked out the book 'The Hobbit’?

SELECT m.name FROM member m
INNER JOIN checkout_item c ON c.member_id = m.id
INNER JOIN book b ON b.id = c.book_id
WHERE b.title = 'The Hobbit';


-- 2) How many people have not checked out anything?


SELECT count(DISTINCT m.id) FROM member m
WHERE m.id NOT IN (
    SELECT DISTINCT c.member_id FROM checkout_item c
);

-- ANSWER: 37


-- 3) What books and movies aren't checked out?


SELECT title FROM movie m
LEFT JOIN checkout_item c ON c.movie_id = m.id
WHERE c.movie_id IS NULL

UNION

SELECT title FROM book b
LEFT JOIN checkout_item c ON c.book_id = b.id
WHERE c.book_id IS NULL;
	

-- 4) Add the book 'The Pragmatic Programmer', and add yourself as a member. Check out 'The Pragmatic Programmer'. Use your query from question 1 to verify that you have checked it out. Also, provide the SQL used to update the database.


INSERT INTO member (name)
VALUES ('Andrei Oliveira');

INSERT INTO book (title)
VALUES ('The Pragmatic Programmer');

INSERT INTO checkout_item (member_id, book_id)
VALUES (43, 11);



-- 5) Who has checked out more that 1 item? 
-- Tip: Research the GROUP BY syntax.

SELECT m.name FROM member m
INNER JOIN checkout_item c ON c.member_id = m.id
GROUP BY m.name
having count(c.member_id) > 1;