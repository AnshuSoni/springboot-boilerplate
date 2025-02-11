insert into category(id, parent_cat_id, name) values
(1, 0, 'clothing'),
(2, 0, 'medicines'),
(3, 0, 'toys'),
(4, 0, 'computers'),
(5, 0, 'accessories'),
(6, 0, 'home decors');

-- Insert primary data in the colors
insert into color(id, name,color_code) values (1,'black', '#000000'),(2, 'white', '#ffffff');
-- Insert some data into  the brand
insert into brand (id,brand_name) values (1,'h&m'),(2,'zudio'),(3,'levis'),(4,'lee'),(5,'United Color of Benetton');