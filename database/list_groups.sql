select *
    from products p, chapters c, groups g
    where p.id = c.product
      and c.id = g.chapter
    order by p.name, c.name, g.name;

