package ru.job4j.chess.firuges;

 public abstract class Figure {


     public abstract Cell position();

     public abstract Cell[] way(Cell source, Cell dest);

   public String icon() {
        return String.format(
                "%s.png", this.getClass().getSimpleName()
        );

   }

   public abstract Figure copy(Cell dest);

     public abstract boolean isWhiteColor();
 }