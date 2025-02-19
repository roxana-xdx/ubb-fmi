; 8. Se dau cuvintele A si B. Sa se obtina cuvântul C:
; bitii 0-5 ai lui C coincid cu bitii 0-5 ai lui B
; bitii 6-12 ai lui C coincid cu bitii 3-9 ai lui A
; bitii 13-15 ai lui C coincid cu bitii 0-2 ai lui A

ASSUME cs:text_,ds:data_

data_ SEGMENT
a dw 0000000000000000b
b dw 1111111111111111b
c dw 0000000000000000b
data_ ENDS

text_ SEGMENT

start:
mov ax, data_
mov ds, ax

; Inceputul programului
mov ax, b ; incepem cu bitii 0-5 ai lui b
and ax, 0000000000111111b ; izolam bitii 0-5
or c, ax ; acum in c avem bitii 0-5 ai lui b, pe pozitiile 0-5
mov ax, a ; ne vom ocupa de bitii 3-9 ai lui a
shl ax, 3 ; trecem bitii 3-9 ai lui a pe pozitiile 6-12
and ax, 0001111111000000b ; izolam bitii care sunt acum pe pozitiile 6-12
or c, ax ; acum in c avem pe pozitiile 0-5 bitii 0-5 ai lui b, iar pe pozitiile 6-12 bitii 3-9 ai lui a
; mai trebuie sa aducem bitii 0-2 ai lui a pe pozitiile 13-15 din c
mov ax, a ; reinitializam ax cu valoarea lui a, avem nevoie de bitii 0-2 pe pozitiile 13-15
shl ax, 13 ; mutam bitii la stanga, bitii 0-2 ajung pe pozitiile 13-15
and ax, 1110000000000000b ; izolam bitii de pe pozitiile 13-15
or c, ax ; rezultatul final

; Terminarea programului
mov ax, 4c00h
int 21h
text_ ENDS

END start

; cazuri de utilizare: a=0000000000000000b, b=1111111111111111b, c=0000000000111111b