; 13. Se dau 2 siruri de octeti A si B. Sa se construiasca sirul R care
; sa contina elementele lui B in ordine inversa urmate de elementele pare ale lui A.

ASSUME cs:text_,ds:data_

data_ SEGMENT
A db 2, 1, 3, 3, 4, 2, 6
L1 equ $-A
B db 4, 5, 7, 6, 2, 1
L2 equ $-B
R db (L1+L2) dup(0)
data_ ENDS

text_ SEGMENT

start:
mov ax, data_
mov ds, ax

; Inceputul programului
; trecem elementele lui B in ordine inversa in R
mov si, L2-1 ; in si avem offsetul ultimului element din sirul B
mov di, 0 ; in di avem offsetul la care adaugam pe rand fiecare elem in R
mov cx, L2 ; numarul de iteratii ale loopului care duce elementele din B in R

repeta: ; elementele lui B sunt adaugate in ordine inversa in sirul R
mov al, B[si]
mov R[di], al
sub si, 1
add di, 1
loop repeta

mov si, 0
mov di, 0
mov cx, L1

repeta2: ; elementele pare ale lui A sunt adaugate in ordine in sirul R
mov al, A[si]
cbw
mov bl, 2
idiv bl ; al = ax:2, ah = ax%2
cmp ah, 0 ; verificam daca elementul e par
je adauga
jne maideparte

adauga: ; elementele pare sunt adaugate in sirul R
mov al, A[si]
mov R[di], al
add si, 1
add di, 1
jmp final

maideparte: ; in cazul in care elementul este impar, se trece la elementul urmator
add si, 1

final:
loop repeta2


; Terminarea programului
mov ax, 4c00h
int 21h
text_ ENDS

END start

; cazuri de utilizare:
; A: 2, 1, 3, 3, 4, 2, 6
; B: 4, 5, 7, 6, 2, 1
; R: 1, 2, 6, 7, 5, 4, 2, 4, 2, 6