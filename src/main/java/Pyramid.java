/**
 * @author Sylvain Tenier
 *
 * This code is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this code.  If not, see <http://www.gnu.org/licenses/>.
 */

public class Pyramid {
    final static char HOTCUP='\u2615';
    final static char CROSS='\u274C';
    final static char SPACE='\u00A0';

    public static void main(String[] args) {
        Pyramid maze=new Pyramid();
        maze.generatePyramidFromHeight(8);
    }

    /** Generates a pyramid on the standard output (the Screen)
     * @param height height of the generated Pyramid
     */
    public void generatePyramidFromHeight(int height){
        for (int ligne=1;ligne<=height;ligne++){
            for (int position=1;position<height*2;position++){
                if((height-ligne+1==position)||(height+ligne-1==position)||(ligne==height)){
                    System.out.print(HOTCUP);
                }else{
                    System.out.print(SPACE);
                }
            }
            System.out.println("");
        }

    }

    /** Generates a pyramid on the standard output (the Screen)
     * @param width width of the displayed Pyramid or number of crosses displayed
     */
    public void generatePyramidFromWidth(int witdh){
        // 1) draw nothing if the provided width is <0
        // 2) draw as many ❌ as the provided width if the width is even
        // 3) draw a ☕ Pyramid if the width is odd
        // Use the provided codes defined at the top of the class
        // *************** YOUR WORK HERE *********************************** //
    }


}
