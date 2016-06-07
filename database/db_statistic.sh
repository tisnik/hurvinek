#!/bin/sh

#
#  (C) Copyright 2016  Pavel Tisnovsky
#
#  All rights reserved. This program and the accompanying materials
#  are made available under the terms of the Eclipse Public License v1.0
#  which accompanies this distribution, and is available at
#  http://www.eclipse.org/legal/epl-v10.html
#
#  Contributors:
#      Pavel Tisnovsky
#

DATABASE=hurvinek.db

echo "Products:"
echo "select count(*) from products;" | sqlite3 ../${DATABASE}

echo "Chapters:"
echo "select count(*) from chapters;" | sqlite3 ../${DATABASE}

echo "Groups:"
echo "select count(*) from chapters;" | sqlite3 ../${DATABASE}

echo "Components:"
echo "select count(*) from components;" | sqlite3 ../${DATABASE}

