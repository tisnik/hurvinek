--
--  (C) Copyright 2016  Pavel Tisnovsky
--
--  All rights reserved. This program and the accompanying materials
--  are made available under the terms of the Eclipse Public License v1.0
--  which accompanies this distribution, and is available at
--  http://www.eclipse.org/legal/epl-v10.html
--
--  Contributors:
--      Pavel Tisnovsky
--

PRAGMA foreign_keys = ON;

create table products (
    id          integer primary key,
    name        text not null,
    description text not null,
    unique (name)
);

create table chapters (
    id          integer primary key,
    product     integer, -- products
    name        text not null,
    foreign key(product) references products(id),
    unique (product,name)
);

create table groups (
    id          integer primary key,
    chapter     integer,
    name        text not null,
    foreign key(chapter) references chapters(id),
    unique (chapter,name)
);

create table components (
    id          integer primary key,
    group_id    integer,
    name        text not null,
    foreign key(group_id) references groups(id),
    unique (group_id,name)
);

