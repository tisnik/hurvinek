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

create table products (
    id          integer primary key,
    name        text,
    description text
);

create table chapters (
    id          integer primary key,
    product     integer, -- products
    name        text
);

create table groups (
    id          integer primary key,
    chapter     integer,
    name        text
);

create table components (
    id          integer primary key,
    group_id    integer,
    name        text
);

