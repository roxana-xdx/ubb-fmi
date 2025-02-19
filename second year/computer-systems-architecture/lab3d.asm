; 13. Se da un sir de dublucuvinte. Sa se determine si sa se salveze in sirul D toti octetii 
; care au numarul de biti din reprezentarea binara mai mic decat o valoare k (declarata in segmentul de date).

ASSUME cs:text_,ds:data_

data_ SEGMENT
S dd 2, 45, 30, 79, 135
L equ $-S
D db L dup(0)
k equ 5
unit db 11111111b
data_ ENDS

text_ SEGMENT

start:
mov ax, data_
mov ds, ax

; Inceputul programului
mov si, 0 ; initializam contorii pentru parcurgerea sirurilor
mov di, 0
mov cx, L
repeta:
mov al, byte ptr S[si] ; luam pe rand fiecare byte
mov unit, 11111111b
shr unit, 8-k+1
cmp unit, al ; verificam daca byte-ul are mai putini biti decat numarul k
jae adauga
jb maideparte

adauga: ; cazul in care byte-ul are mai putini biti decat k
mov D[di], al ; adaugam byte-ul in D
add si, 1
add di, 1
jmp final

maideparte: ; cazul in care byte-ul are k sau mai multi biti
add si, 1

final:
loop repeta

; Terminarea programului
mov ax, 4c00h
int 21h
text_ ENDS

END start

; cazuri de utilizare:
; S: 2, 45, 30, 79, 135
; S in binar: 00000000 00000000 00000000 00000010, 00000000 00000000 00000000 00101101, 00000000 00000000 00000000 00011110,
;			  00000000 00000000 00000000 01001111, 00000000 00000000 00000000 10000111
; D in binar: 00000010, 00000000, 00000000, 00000000, 00000000, 00000000, 00000000, 00000000, 00000000, 00000000, 00000000, 00000000,
;			  00000000, 00000000, 00000000, 00000000
; D: 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0