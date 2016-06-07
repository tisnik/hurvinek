select *
    from products p, chapters c, groups g, components k
    where       p.id = c.product
      and       c.id = g.chapter
      and k.group_id = g.id
    order by p.name, c.name, g.name, k.name;

