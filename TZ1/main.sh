#!/bin/bash

# задаем переменные для исходной и выходной директорий
in_dir=$1
out_dir=$2

mkdir -p "$out_dir"
# функция для копирования файлов
copy_file() {
    local in_file=$1
    local out_file=$2
    local counter=1 # если номера файлов совпадают, то добавляем номер, который зависит от счетчика
    while [[ -f "$out_file" ]]; do # проверяем существует ли файл с таким же именем 
        out_file="${2}_$counter" #добавляем счетчик к имени файла
        ((counter++))
    done
    cp "$in_file" "$out_file" #копирование файла
}

echo "Файлы, которые находятся вне папок входной директории:"
find "$in_dir" -maxdepth 1 -type f -exec basename {} \; 

echo "Папки, находящиеся во входной директории:"
find "$in_dir" -mindepth 1 -maxdepth 1 -type d -exec basename {} \;

find "$in_dir" -type f -print0 | while IFS= read -r -d $'\0' file; do
    filename=$(basename -- "$file") #находи файл
    copy_file "$file" "$out_dir/$filename" #копируем в директорию с помощью функции
done

echo "Все файлы в выходной директории:"
ls "$out_dir" | while read file; do
    echo "$file"
done
