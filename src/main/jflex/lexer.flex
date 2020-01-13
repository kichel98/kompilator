package core;
/* --------------------------Usercode Section------------------------ */
import cup11b.*; 
import java_cup.runtime.*;
%%
   
/* -----------------Options and Declarations Section----------------- */
   
/* 
   The name of the class JFlex will create will be Lexer.
   Will write the code to the file Lexer.java. 
*/
%public
%class Lexer

/*
  The current line number can be accessed with the variable yyline
  and the current column number with the variable yycolumn.
*/
%line
%column

%cup
   
/*
  Declarations
   
  Code between %{ and %}, both of which must be at the beginning of a
  line, will be copied letter to letter into the lexer class source.
  Here you declare member variables and functions that are used inside
  scanner actions.  
*/
%{
   ComplexSymbolFactory symbolFactory;

   public Lexer(java.io.Reader in, ComplexSymbolFactory sf){
      this(in);
      symbolFactory = sf;
   }

  private Symbol symbol(String name, int sym) {
      return symbolFactory.newSymbol(name, sym, new ComplexSymbolFactory.Location(yyline+1,yycolumn+1,yychar),
       new ComplexSymbolFactory.Location(yyline+1,yycolumn+yylength(),yychar+yylength()));
  }

  private Symbol symbol(String name, int sym, Object val) {
      ComplexSymbolFactory.Location left = new ComplexSymbolFactory.Location(yyline+1,yycolumn+1,yychar);
      ComplexSymbolFactory.Location right= new ComplexSymbolFactory.Location(yyline+1,yycolumn+yylength(), yychar+yylength());
      return symbolFactory.newSymbol(name, sym, left, right,val);
  }
  private Symbol symbol(String name, int sym, Object val,int buflength) {
      ComplexSymbolFactory.Location left = new ComplexSymbolFactory.Location(yyline+1,yycolumn+yylength()-buflength,yychar+yylength()-buflength);
      ComplexSymbolFactory.Location right= new ComplexSymbolFactory.Location(yyline+1,yycolumn+yylength(), yychar+yylength());
      return symbolFactory.newSymbol(name, sym, left, right,val);
  }
  private void error(String message) {
    System.out.println("Error at line "+(yyline+1)+", column "+(yycolumn+1)+" : "+message);
  }
%}
   
%eofval{
     return symbolFactory.newSymbol("EOF", sym.EOF, new ComplexSymbolFactory.Location(yyline+1,yycolumn+1,yychar), new ComplexSymbolFactory.Location(yyline+1,yycolumn+1,yychar+1));
%eofval}

/*
  Macro Declarations
  
  These declarations are regular expressions that will be used latter
  in the Lexical Rules Section.  
*/
   
/* A line terminator is a \r (carriage return), \n (line feed), or
   \r\n. */
LineTerminator = \r|\n|\r\n
   
/* White space is a line terminator, space, tab, or line feed. */
WhiteSpace     = {LineTerminator} | [ \t\f]
   
/* A literal integer is is a number beginning with a number between
   one and nine followed by zero or more numbers between zero and nine
   or just a zero.  */
num = 0 | [1-9][0-9]*
   
/* A identifier integer is a word beginning a letter between A and
   Z, a and z, or an underscore followed by zero or more letters
   between A and Z, a and z, zero and nine, or an underscore. */
pidentifier = [_a-z]+
   
%%
/* ------------------------Lexical Rules Section---------------------- */
   
/*
   This section contains regular expressions and actions, i.e. Java
   code, that will be executed when the scanner matches the associated
   regular expression. */
   
   /* YYINITIAL is the state at which the lexer begins scanning.  So
   these regular expressions will only be matched if the scanner is in
   the start state YYINITIAL. */
   
<YYINITIAL> {
   
   "[]"                      { }

    /* Return the token SEMI declared in the class sym that was found. */
    "DECLARE"                { return symbol("declare",sym.DECLARE); }
    "BEGIN"                  { return symbol("begin",sym.BEGIN); }
    "END"                    { return symbol("end",sym.END); }

    "ASSIGN"                 { return symbol("assign", sym.ASSIGN); }
    "IF"                     { return symbol("if", sym.IF); }
    "THEN"                   { return symbol("then", sym.THEN); }
    "ELSE"                   { return symbol("else", sym.ELSE); }
    "ENDIF"                  { return symbol("endif", sym.ENDIF); }
    "WHILE"                  { return symbol("while", sym.WHILE); }
    "DO"                     { return symbol("do", sym.DO); }
    "ENDWHILE"               { return symbol("endwhile", sym.ENDWHILE); }
    "ENDDO"                  { return symbol("enddo", sym.ENDDO); }
    "FOR"                    { return symbol("for", sym.FOR); }
    "FROM"                   { return symbol("from", sym.FROM); }
    "TO"                     { return symbol("to", sym.TO); }
    "DOWNTO"                 { return symbol("downto", sym.DOWNTO); }
    "ENDFOR"                 { return symbol("endfor", sym.ENDFOR); }
    "READ"                   { return symbol("read", sym.READ); }
    "WRITE"                  { return symbol("write", sym.WRITE); }

    "PLUS"                   { return symbol("+", sym.PLUS); }
    "MINUS"                  { return symbol("-", sym.MINUS); }
    "TIMES"                  { return symbol("*", sym.TIMES); }
    "DIV"                    { return symbol("/", sym.DIV); }
    "MOD"                    { return symbol("%", sym.MOD); }

    "("                      { return symbol("(", sym.LPAR); }
    ")"                      { return symbol(")", sym.RPAR); }
    ":"                      { return symbol(":", sym.COLON); }
    ";"                      { return symbol(";", sym.SEMICOLON); }
    ","                      { return symbol(",", sym.COMMA); }

    {num}                    { return symbol("num", sym.num, new Integer(yytext())); }
   
    {pidentifier}            { return symbol("ident", sym.pidentifier, yytext()); }
   
    /* Don't do anything if whitespace is found */
    {WhiteSpace}       { /* just skip what was found, do nothing */ }
}


/* No token was found for the input so through an error.  Print out an
   Illegal character message with the illegal character that was found. */
[^]                    { throw new Error("Unexpected token <"+yytext()+">"); }
