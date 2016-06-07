select *
    from products p, chapters c
    where p.id=c.product
    order by p.name, c.name;

