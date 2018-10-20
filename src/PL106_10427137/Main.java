package PL106_10427137;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

class G {
  static Scanner sInput = new Scanner( System.in ) ;
  static int     sPsid = -1 ; // 
  static int     sTest = 0 ;
} // class G

class TokenInfo { //   Store Token Information
  private String mValue ; //   token Name
  private int  mTable ; //   token table which table it's
  private int  mEntry ; //   index in the table( ID's PSID )
  private float  mValue_num ; //   int or float value
  private int  mType = 0  ; //   type of tb5    3--> integer 4-->real
  private int mInputColumn ; // input Column
  private Table7 mTable7; // Table7
  private int mDomain ; // it's Domain
  
  
  public TokenInfo( String sValue, int sInputColumn ) { 
    this.mValue = sValue ;
    this.mTable = 0  ; //   initial value
    this.mEntry = 0  ; //   initial value
    this.mInputColumn = sInputColumn ;
  } // TokenInfo()
     
  public TokenInfo( String sValue, int iTable, int sInputColumn ) {
    this.mValue = sValue ;
    this.mTable = iTable ;
    this.mEntry = 0      ;
    this.mInputColumn = sInputColumn ;
  } // TokenInfo()
     
  public TokenInfo( String sValue, int iTable, int iEntry, int sInputColumn ) {
    this.mValue = sValue ;
    this.mTable = iTable ;
    this.mEntry = iEntry ;
    this.mType = 0 ;
    this.mInputColumn = sInputColumn ;
  } // TokenInfo()
     
  public TokenInfo( float iNum, String sValue, int iTable, int iEntry, int sInputColumn ) {
    this.mValue = sValue ;
    this.mTable = iTable ;
    this.mEntry = iEntry ;
    this.mType = 0 ;
    this.mValue_num = iNum ;
    this.mInputColumn = sInputColumn ;
  } // TokenInfo()
     
  public TokenInfo( int iType, float iNum, String sValue, int iTable, int iEntry, int sInputColumn ) { 
    this.mValue = sValue ;
    this.mTable = iTable ;
    this.mEntry = iEntry ;
    this.mType = iType ;
    this.mValue_num = iNum ;
    this.mInputColumn = sInputColumn ;
  } // TokenInfo()
     
  public TokenInfo() {
    //   TODO Auto-generated constructor stub
  } // TokenInfo()

  public void SetValue( String sValue ) {
    this.mValue = sValue ;
  } // SetValue()
     
  public void SetTable( int iTable ) {
    this.mTable = iTable ;
  } // SetTable()

  public void SetEntry( int iEntry ) {
    this.mEntry = iEntry ;
  } // SetEntry()
      
  public void SetAll( String sValue, int iTable, int iEntry ) {
    this.mValue = sValue ;
    this.mTable = iTable ;
    this.mEntry = iEntry ;
  } // SetAll()

  public void SetValue_num( float ivalue_num ) {
    this.mValue_num = ivalue_num ;
  } // SetValue_num()
     
  public void SetType( int iType ) {
    this.mType = iType ;
  } // SetType()

  public void SetTable7( Table7 iTable7 ) {
    this.mTable7 = iTable7 ;    
  } // SetTable7()
  
  public void SetDomain(  int nDomain ) {
    this.mDomain = nDomain ;
  } // SetDomain()
  
  public void SetInputColumn( int mInputColumn ) {
    this.mInputColumn = mInputColumn ;
  } // SetInputColumn()
  
  public String GetValue() {
    return this.mValue ;
  } // GetValue()
     
  public int GetTable() {
    return this.mTable ;
  } // GetTable()
        
  public int GetEntry() {
    return this.mEntry ;
  } // GetEntry()
     
  public float GetValue_num( ) {
    return  this.mValue_num ;
  } // GetValue_num()
     
  public int GetType( ) {
    return this.mType ;
  } // GetType()
         
  public Table7 GetTable7() {
    return this.mTable7 ;    
  } // GetTable7()
  
  public int GetInputColumn() {
    return this.mInputColumn ;
  } // GetInputColumn()
  
  public int GetDomain() {
    return this.mDomain ;
  } // GetDomain()
  
  public String ToString_value() { //   output the token Value
    return this.GetValue() + "\t" ;
  } // ToString_value()
     
  public String ToString_table() { //   ouput the token table and entry
    return "(" + this.GetTable() + "," + this.GetEntry() + ")"  ;
  } // ToString_table()
     
} // class TokenInfo
  
//   ***********End of TokenInfo Class*****************// 
// ***********************Start of LineInfo Class*********// 
class LineInfo { //  Store TokenInfo inside one Line
  ArrayList<TokenInfo> mLineList ; //   Store TokenInfo inside one Line   
  public LineInfo() {
    mLineList = new ArrayList<TokenInfo>() ;
  } // LineInfo()
    
  public LineInfo( ArrayList<TokenInfo> oLinelist ) {
    mLineList = oLinelist ;
  } // LineInfo()
    
  public void InsertTokenInfo( TokenInfo oTokenInfo ) {
    mLineList.add( oTokenInfo ) ;
  } // InsertTokenInfo()
    
  public void InsertTokenInfo_spe( String sValue, int iTable, int iEntry ) {
    TokenInfo oTokenInfo = new TokenInfo( sValue, iTable, iEntry ) ;
  } // InsertTokenInfo_spe()
    
  public ArrayList<TokenInfo> GetLineList() {
    return this.mLineList ;
  } // GetLineList()
   
  public void SetLineList( ArrayList<TokenInfo> oLineList ) {
    this.mLineList = oLineList ;
  } // SetLineList()
       
  public String ToString( String temp ) { //   output Lineinfo
    String opt = "" ;
    for ( int i = 0 ; i < mLineList.size() ; i++ ) 
      opt += mLineList.get( i ).ToString_value() ;  
    opt +=  temp + "\n" ;
     
    for ( int i = 0 ; i < mLineList.size() ; i++ ) 
      opt += mLineList.get( i ).ToString_table() ;  
    opt += "\n" ;
    return opt ;
  } // ToString()     
} // class LineInfo

  // **********************end of LineInfo Class*******************************//  

// **************************start of Lexical Class********************************/ 
class Lexical { //   Do the Lexical analyze and PeekToken GetToken
// *************************private area************************************************/ 
  private int mInputColumn = 0 ; //   current Input Column 
  private int mInputRow = 0 ; //   Current Input Row

  private int mCurrentColumn = 0 ; //  current lexicalFileª½¦æ
  private int mCurrentRow = -1 ; //   current lexicalFile¾î¦æ
  
  private boolean mTerminate = false ;
  
  private TokenInfo mRegTokenInfo = new TokenInfo()  ;
  private String[] mRegister = { "int", "float", "char", "string", 
                                 "bool", "void", "if", "else", "while", "do", "return" } ; 
  
  private ArrayList<TokenInfo> mTable5 = new ArrayList<TokenInfo>() ; 
  
  private ArrayList<LineInfo> mLexicalFile = new ArrayList<LineInfo>() ; 
  
  private ArrayList<Function> mFunction = new ArrayList<Function>() ;
  // ******************************************************************/            
  public Lexical() {
  } // Lexical()
     
  
  // *****************************Get**********************************//
  public int GetCurrentColumn() {
    return this.mCurrentColumn ;
  } // GetCurrentColumn()
  
  public int GetCurrentRow() {
    return this.mCurrentRow ;
  } // GetCurrentRow()
  
  public ArrayList<LineInfo> GetLexicalFile() {
    return this.mLexicalFile ;    
  } // GetLexicalFile()
  
  public int GetInputRow() {
    return this.mInputRow ;
  } // GetInputRow()
  
  public int GetInputColumn() {
    return this.mInputColumn ;
  } // GetInputColumn()
  
  public boolean GetTerminate() {
    return this.mTerminate ;
  } // GetTerminate()
    
  public ArrayList<Function> GetFunction() {
    return this.mFunction ;    
  } // GetFunction()
  
  public ArrayList<TokenInfo> GetTable5() {
    return this.mTable5 ;    
  } // GetTable5()
  // ******************************************************************//
  
  // ***************************Set***********************************//
  public void SetLexicalFile( ArrayList<LineInfo> aLexicalFile ) {
    this.mLexicalFile = aLexicalFile ;    
  } // SetLexicalFile()
    
    
  public void SetCurrentColumn( int iTempColumn ) {
    this.mCurrentColumn = iTempColumn ;
  } // SetCurrentColumn()

  public void SetCurrentRow( int iTempRow ) { 
    this.mCurrentRow = iTempRow ;     
  } // SetCurrentRow()
   
  public void SetFunction( ArrayList<Function>  aFunction ) {
    this.mFunction = aFunction ;    
  } // SetFunction()
    
  public void SetTerminate( boolean bTerminate ) {
    this.mTerminate = bTerminate ;
  } // SetTerminate()
  
  public void SetTable5( ArrayList<TokenInfo> aTable5 ) {
    this.mTable5 = aTable5 ;    
  } // SetTable5()
  
  public void SetInputColumn( int iColumn ) {
    this.mInputColumn = iColumn  ;
  } // SetInputColumn()
  // *****************************************************************//
  
  private int  IsTable2( String inputString  ) {
    for ( int i = 0 ; i < this.mRegister.length ; i++ ) {
      if ( inputString.equals( this.mRegister[ i ] ) ) 
        return i + 1 ;
    } // for
    
    return 0 ;
  } // IsTable2()
  
  private TokenInfo SetToken( int tokenNum, char secondChar, char threeChar,
                              String inputString, int EntryOne, int EntryTwo, int EntryThree ) {
    TokenInfo tempTokenInfo = new TokenInfo() ;
    int tempRow = mInputRow ; //   last Value
    if ( tokenNum == 3 ) {
      mInputRow++ ; //   get next char
      if ( mInputRow < inputString.length() && inputString.charAt( mInputRow ) == secondChar ) { 
        mInputRow++ ;
        tempTokenInfo = new TokenInfo( inputString.
                        substring( tempRow, mInputRow ), 1, EntryTwo, this.mInputColumn ) ;
        mInputRow-- ;
      } // if
      else if ( mInputRow < inputString.length() && inputString.charAt( mInputRow ) == threeChar ) {
        // it's <<
        mInputRow++ ;
        tempTokenInfo = new TokenInfo( inputString.
                        substring( tempRow, mInputRow ), 1, EntryThree, this.mInputColumn ) ;
        mInputRow-- ;
      } // else if 
      else { //   only '<'
        tempTokenInfo = new TokenInfo( inputString.
                        substring( tempRow, mInputRow ), 1, EntryOne, this.mInputColumn ) ;
        mInputRow-- ;
      } // else   
    } // if
    else if ( tokenNum == 2 ) {
      mInputRow++ ; //   get next char
      if ( mInputRow < inputString.length() && inputString.charAt( mInputRow ) == secondChar ) { 
        mInputRow++ ;
        tempTokenInfo = new TokenInfo( inputString.
                        substring( tempRow, mInputRow ), 1, EntryTwo, this.mInputColumn ) ; 
        mInputRow-- ;
      } // if
      else { 
        tempTokenInfo = new TokenInfo( inputString.
                        substring( tempRow, mInputRow ), 1, EntryOne, this.mInputColumn ) ; // only 
        mInputRow-- ; //   back one char
      } // else    
    } // else if
   
    return tempTokenInfo ;
  } // SetToken()
  
  public void GetLine() {
    String inputString = ""  ;
    if ( G.sInput.hasNextLine() == true )
      inputString = G.sInput.nextLine() ;
    else {
      // System.out.println( "> Our-C exited ..." ) ;
      System.exit( 0  ) ;
    } // else

    this.mInputColumn = this.mInputColumn + 1 ;
    this.mInputRow    = 0 ;
    TokenInfo tempTokenInfo  ;
    ArrayList<TokenInfo> tempTokenArrayList =  new ArrayList<TokenInfo>() ;
    while ( inputString.isEmpty() == false && mInputRow < inputString.length() ) {
      // ******************Skip WhiteSpace********************************/
      if ( inputString.charAt( mInputRow ) == '\t' || inputString.charAt( mInputRow ) == '\n'
           || inputString.charAt( mInputRow ) == ' ' ) {
      } // if
      // *******************************Skip Annotation***************************/
      else if ( inputString.charAt( mInputRow ) == '/' ) { //  
        int tempRow = mInputRow ;
        mInputRow++ ;
        if (  mInputRow < inputString.length() && inputString.charAt( mInputRow ) == '/' ) {
          mInputRow = inputString.length() ; //   skip till last index
        } // if
        // *****************************************only '/'*************************************/ 
        else if ( mInputRow < inputString.length() && inputString.charAt( mInputRow ) == '=' ) { // '/='
          mInputRow++ ;
          tempTokenInfo = new TokenInfo( inputString.
                          substring( tempRow, mInputRow ), 1, 31, this.mInputColumn ) ;
          tempTokenArrayList.add( tempTokenInfo ) ; //   add to temp TokenArrayList
          mInputRow-- ; 
        } // else if 
        else { 
          tempTokenInfo = new TokenInfo( inputString.
                          substring( tempRow, mInputRow ), 1, 12, this.mInputColumn ) ;
          tempTokenArrayList.add( tempTokenInfo ) ; //   add to temp TokenArrayList
          mInputRow-- ;
        } // else
      } // else if
      // *****************************Three delimiter******************************/
      else if (  inputString.charAt( mInputRow ) == '<' ) { //   it's '<'
        tempTokenArrayList.add( this.SetToken( 3, '<', '=', inputString, 14, 36, 4 ) ) ;
      // **********************************************************************/ 
      } // else if
      else if ( inputString.charAt( mInputRow ) == '>' ) { //   it's '>'
        tempTokenArrayList.add( this.SetToken( 3, '>', '=', inputString, 13, 35, 3 ) ) ;
      // *******************************************************/ 
      } // else if
      else if ( inputString.charAt( mInputRow ) == '+' ) { //   it's '+'
        tempTokenArrayList.add( this.SetToken( 3, '+', '=', inputString, 9, 33, 28 ) ) ;
      } // else if
      else if ( inputString.charAt( mInputRow ) == '-' ) { //   it's '-'
        tempTokenArrayList.add( this.SetToken( 3, '-', '=', inputString, 10, 34, 29 ) ) ;
      } // else if
      // ******************************************************************************/
      // *****************************only one Delimeter********************/
      else if ( inputString.charAt( mInputRow ) == ';' )  { //   it's ';'
        tempTokenInfo = new TokenInfo( inputString.
                        substring( mInputRow, mInputRow + 1 ), 1, 5, this.mInputColumn ) ;
        tempTokenArrayList.add( tempTokenInfo ) ; //   add to temp TokenArrayList 
      } // else if
      else if ( inputString.charAt( mInputRow ) == '(' )  { //   it's '('
        tempTokenInfo = new TokenInfo( inputString.
                        substring( mInputRow, mInputRow + 1 ), 1, 6, this.mInputColumn ) ;
        tempTokenArrayList.add( tempTokenInfo ) ; //   add to temp TokenArrayList
      } // else if
      else if ( inputString.charAt( mInputRow ) == ')' )  { //   it's ')'
        tempTokenInfo = new TokenInfo( inputString.
                        substring( mInputRow, mInputRow + 1 ), 1, 7, this.mInputColumn ) ;
        tempTokenArrayList.add( tempTokenInfo ) ; //   add to temp TokenArrayList
      } // else if
      else if ( inputString.charAt( mInputRow ) == '[' )  { //   it's '['
        tempTokenInfo = new TokenInfo( inputString.
                        substring( mInputRow, mInputRow + 1 ), 1, 15, this.mInputColumn ) ;
        tempTokenArrayList.add( tempTokenInfo ) ; //   add to temp TokenArrayList
      } // else if
      else if ( inputString.charAt( mInputRow ) == ']' )  { //   it's ']'
        tempTokenInfo = new TokenInfo( inputString.
                        substring( mInputRow, mInputRow + 1 ), 1, 16, this.mInputColumn ) ;
        tempTokenArrayList.add( tempTokenInfo ) ; //   add to temp TokenArrayList
      } // else if
      else if ( inputString.charAt( mInputRow ) == '{' )  { //   it's '{'
        tempTokenInfo = new TokenInfo( inputString.
                        substring( mInputRow, mInputRow + 1 ), 1, 17, this.mInputColumn ) ;
        tempTokenArrayList.add( tempTokenInfo ) ; //   add to temp TokenArrayList
      } // else if
      else if ( inputString.charAt( mInputRow ) == '}' )  { //   it's '}'
        tempTokenInfo = new TokenInfo( inputString.
                        substring( mInputRow, mInputRow + 1 ), 1, 18, this.mInputColumn ) ;
        tempTokenArrayList.add( tempTokenInfo ) ; //   add to temp TokenArrayList
      } // else if
      else if ( inputString.charAt( mInputRow ) == '^' )  { //   it's '^'
        tempTokenInfo = new TokenInfo( inputString.
                        substring( mInputRow, mInputRow + 1 ), 1, 20, this.mInputColumn ) ;
        tempTokenArrayList.add( tempTokenInfo ) ; //   add to temp TokenArrayList
      } // else if
      else if ( inputString.charAt( mInputRow ) == ',' )  { //   it's ','
        tempTokenInfo = new TokenInfo( inputString.
                       substring( mInputRow, mInputRow + 1 ), 1, 37, this.mInputColumn ) ;
        tempTokenArrayList.add( tempTokenInfo ) ; //   add to temp TokenArrayList
      } // else if
      else if ( inputString.charAt( mInputRow ) == '?' )  { //   it's '?'
        tempTokenInfo = new TokenInfo( inputString.
                        substring( mInputRow, mInputRow + 1 ), 1, 38, this.mInputColumn ) ;
        tempTokenArrayList.add( tempTokenInfo ) ; //   add to temp TokenArrayList
      } // else if
      else if ( inputString.charAt( mInputRow ) == ':' )  { //   it's ':'
        tempTokenInfo = new TokenInfo( inputString.
                        substring( mInputRow, mInputRow + 1 ), 1, 39, this.mInputColumn ) ;
        tempTokenArrayList.add( tempTokenInfo ) ; //   add to temp TokenArrayList
      } // else if
      // ***********************Double Delimiter********************************//
      else if ( inputString.charAt( mInputRow ) == '*' )  { //   it's '*'
        tempTokenArrayList.add( this.SetToken( 2, '=', 'f', inputString, 11, 30, 0 )  )  ; 
      } // else if
      else if ( inputString.charAt( mInputRow ) == '%' )  { //   it's '%'
        tempTokenArrayList.add( this.SetToken( 2, '=', 'f', inputString, 19, 32, 0 )  )  ; 
      } // else if
      else if (  inputString.charAt( mInputRow ) == '&'  ) { // it's '&'
        tempTokenArrayList.add( this.SetToken( 2, '&', 'f', inputString, 23, 26, 0 )  )  ;
      } // else if
      else if (  inputString.charAt( mInputRow ) == '|'  ) { // it's '|'
        tempTokenArrayList.add( this.SetToken( 2, '|', 'f', inputString, 24, 27, 0 )  )  ; 
      } // else if
      else if (  inputString.charAt( mInputRow ) == '='  ) { // it's '='
        tempTokenArrayList.add( this.SetToken( 2, '=', 'f', inputString, 8, 21, 0 )  )  ; 
      } // else if
      else if (  inputString.charAt( mInputRow ) == '!'  ) { // it's '!'
        tempTokenArrayList.add( this.SetToken( 2, '=', 'f', inputString, 25, 22, 0 )  )  ;
      } // else if
      // *************************************is char**************************************/
      else if (  inputString.charAt( mInputRow ) == 39  ) { // it's '''
        int tempRow = mInputRow ;  
        if ( tempRow + 2 < inputString.length() && inputString.charAt( tempRow + 2  )  == 39 ) {           
          tempTokenInfo = new TokenInfo( inputString.substring( tempRow, tempRow + 3  ),
                                         6, 0, this.mInputColumn ) ;
          tempTokenArrayList.add( tempTokenInfo ) ; //   add to temp TokenArrayList                        
          mInputRow = tempRow + 2 ;   
        } // if
        else {
          if ( tempRow + 2 < inputString.length() )
            tempTokenInfo = new TokenInfo( inputString.substring( tempRow, tempRow  + 3  ),
                                           0, 0, this.mInputColumn ) ;
          else
            tempTokenInfo = new TokenInfo( inputString.substring( tempRow, tempRow  + 1  ),
                                           0, 0, this.mInputColumn ) ;
          
          tempTokenArrayList.add( tempTokenInfo ) ; //   
          mInputRow = tempRow + 2 ;
        } // else
      } // else if 
      // ***************************is String*********************/
      else if ( inputString.charAt( mInputRow ) == '"' ) { // it's '"'
        int tempRow = mInputRow ;  
        int tempColumn = mInputColumn ;
        String tempString = ""   ;
        boolean stop = false ;
        tempRow++ ;
        ArrayList<TokenInfo> tempArrayList7 = tempTokenArrayList ;
        tempTokenInfo = new TokenInfo( inputString.
                        substring( mInputRow, mInputRow + 1 ), 7, 1, this.mInputColumn ) ;
        tempArrayList7.add( tempTokenInfo ) ;
        int pastColumn = 0 ;
        while ( stop == false ) {
          if ( tempRow != inputString.length() ) {
            if ( inputString.charAt( tempRow ) == '"' ) { 
              // tempString = tempString + "\"" ;
              tempTokenInfo = new TokenInfo( tempString, 7, 0, pastColumn ) ;
              tempArrayList7.add( tempTokenInfo ) ; //   add to temp TokenArrayList  
            
              tempTokenInfo = new TokenInfo( inputString.substring( tempRow, tempRow + 1 ),
                                             7, 1, tempColumn ) ;
              tempArrayList7.add( tempTokenInfo ) ; //   add to temp TokenArrayList  
            
              tempTokenArrayList = tempArrayList7 ;
              mInputRow = tempRow ;
              mInputColumn = tempColumn ;
              stop = true ;
            } // if
            else {
              tempString = tempString + String.valueOf( inputString.charAt( tempRow ) )  ;  
              // System.out.println( tempString ) ;
              pastColumn = tempColumn ;
              tempRow++ ;
            } // else 
          } // if
          else {
            tempString = tempString + String.valueOf(  '\n' )  ; 
            if ( G.sInput.hasNextLine() == false ) {
              tempTokenInfo = new TokenInfo( '"' + tempString, 0, 0, tempColumn ) ;
              tempTokenArrayList.add( tempTokenInfo ) ; //   add to temp TokenArrayList  
              stop = true ;
            } // if
            else {
              inputString = G.sInput.nextLine() ; 
              tempColumn++ ;
              tempRow = 0 ;
            } // else
          } // else  
        } // while        
      } // else if 
      // *********************Float or Real**********************/
      // ***************************dot start***********************/
      else if ( inputString.charAt( mInputRow ) == '.' ) { //   dot start float  .4 .4564
        int tempRow = mInputRow ;
        mInputRow++ ;
        if ( mInputRow < inputString.length() &&  inputString.charAt( mInputRow ) >= '0' &&
             inputString.charAt( mInputRow )  <= '9' ) { //   Token is 0~9
          boolean stop = false ;  //   when its's not Num stop
          while ( mInputRow < inputString.length() && stop == false ) {
            if ( inputString.charAt( mInputRow ) >= '0' && inputString.charAt( mInputRow )  <= '9' ) {
              mInputRow++ ;           
            } // if
            else {
              stop = true ;
            } // else
          } // while     
  
          mInputRow-- ;  
          float tempFloat = Float.parseFloat( inputString.
                            substring( tempRow, mInputRow + 1 ) ) ;
          tempTokenInfo =
          new TokenInfo( 4, tempFloat, inputString.
          substring( tempRow, mInputRow + 1 ), 4, 0, this.mInputColumn ) ;
          tempTokenArrayList.add( tempTokenInfo ) ; //   add to temp TokenArrayList
        } // if
        else {
          tempTokenInfo = new TokenInfo( inputString.
                          substring( tempRow, mInputRow ), 0, 0, this.mInputColumn ) ;
          tempTokenArrayList.add( tempTokenInfo ) ; //   add to temp TokenArrayList
          mInputRow-- ; 
        } // else
      } // else if
      // ******************************************************************/
      // *********************************Number start****************************************/
      else if ( inputString.charAt( mInputRow ) >= '0' && inputString.charAt( mInputRow )  <= '9' ) { 
        int tempRow = mInputRow ;
        int type = 0  ;
        boolean stop = false ;
        int dotCount = 0 ;
        while ( mInputRow < inputString.length() && stop == false  ) {
          if ( ( ( inputString.charAt( mInputRow ) >= '0' && inputString.charAt( mInputRow )  <= '9' ) ||
                 inputString.charAt( mInputRow ) == '.' ) && dotCount == 0 ) {
            if ( inputString.charAt( mInputRow ) == '.'  )
              dotCount++ ;
            mInputRow++ ;       
          } // if
          else if ( ( inputString.charAt( mInputRow ) >= '0' &&
                      inputString.charAt( mInputRow )  <= '9' ) && dotCount == 1 ) {
            mInputRow++ ;
          } // else if
          else {
            stop = true ;
          } // else
        } // while  
        
        mInputRow-- ;  
        float tempFloat = Float.parseFloat( inputString.substring( tempRow, mInputRow + 1 ) ) ;
        if ( dotCount == 1 )
          type = 4 ; //   if have dot it's Float type4
        else if ( dotCount == 0 )
          type = 3 ; //   if have no dot it's Real type3         
        tempTokenInfo = 
        new TokenInfo( type, tempFloat, inputString.substring( tempRow, mInputRow + 1 ),
                       type, 0, this.mInputColumn ) ; 
        tempTokenArrayList.add( tempTokenInfo ) ; //   add to temp TokenArrayList
      } // else if
      // ***********************************************It's ID***************************/
      else if ( ( inputString.charAt( mInputRow ) >= 'A' && inputString.charAt( mInputRow ) <= 'Z' ) ||
                ( inputString.charAt( mInputRow ) >= 'a' && inputString.charAt( mInputRow ) <= 'z' ) ) {
        int tempRow = mInputRow ;
        boolean stop = false ;
        mInputRow++ ;
        while ( mInputRow < inputString.length() && stop == false ) {
          if  ( ( inputString.charAt( mInputRow ) >= 'A' && inputString.charAt( mInputRow ) <= 'Z' ) ||
                ( inputString.charAt( mInputRow ) >= 'a' && inputString.charAt( mInputRow ) <= 'z' ) ||
                ( inputString.charAt( mInputRow ) >= '0' && inputString.charAt( mInputRow ) <= '9' ) ||
                ( inputString.charAt( mInputRow ) == '_'  ) ) {
            mInputRow++ ;
          } // if
          else {
            stop = true ;
          } // else
        } // while
       
        mInputRow-- ;
        // *************************Boolean ***************************//
        String tempString = inputString.substring( tempRow, mInputRow + 1  ) ;
        int table2Num  = this.IsTable2(  tempString ) ;
        
        if ( tempString.equals( "true" ) == true || tempString.equals( "false" ) == true  ) {
          if ( tempString.equals( "true" ) == true )
            tempTokenInfo = new TokenInfo( inputString.
                            substring( tempRow, mInputRow + 1 ), 8, 1, this.mInputColumn ) ;
          else 
            tempTokenInfo = new TokenInfo( inputString.
                            substring( tempRow, mInputRow + 1 ), 8, 0, this.mInputColumn ) ;  
        } // if
        else if (  table2Num != 0  ) {
          tempTokenInfo = new TokenInfo( tempString, 2, table2Num, this.mInputColumn ) ;   
        } // else if
        // ****************************************************//
        else {
          tempTokenInfo = new TokenInfo( tempString, 5, 0, this.mInputColumn ) ;   
        } // else
        
        tempTokenArrayList.add( tempTokenInfo ) ; //   add to temp TokenArrayList
      } // else if
      // *******************************************************/
      else {
        tempTokenInfo = new TokenInfo( inputString.
                        substring( mInputRow, mInputRow + 1 ), 0, 0, this.mInputColumn ) ; 
        tempTokenArrayList.add( tempTokenInfo ) ; //   add to temp TokenArrayList 
      } // else
      
      mInputRow++ ;
    } // while
  
    if ( tempTokenArrayList.size() != 0 ) {
      LineInfo tempLineInfo = new LineInfo( tempTokenArrayList ) ; //   
      this.mLexicalFile.add( tempLineInfo ) ;
      this.mCurrentColumn = this.mLexicalFile.size() - 1 ; //   
      this.mCurrentRow = -1 ; //   Initial Value
    } // if
  } // GetLine()
     
  public TokenInfo PeekToken() { //   Check the next TokenInfo
    try {
      int tempColumn = this.mCurrentColumn ; //   store current Column location
      int tempRow    = this.mCurrentRow ; //   store current row location
      if ( this.mLexicalFile.size() == 0 ) { //       
        while ( this.mLexicalFile.size() == 0 && this.mTerminate == false ) {
          this.GetLine(); 
        } // while
      
        tempColumn = this.mCurrentColumn ;
        tempRow =  this.mCurrentRow  ;  //   Row start with -1
      } // if
      else if ( tempRow + 1 == this.mLexicalFile.
                get( tempColumn ).GetLineList().size()  && 
                tempColumn < this.mLexicalFile.size() -1 ) {
        tempColumn++ ;    //   Jump to next Column
        tempRow = -1  ;  //   Row start with -1
      } // else if 
      else if ( tempRow + 1  == this.mLexicalFile.
                get( tempColumn ).GetLineList().size()  && 
                tempColumn   == this.mLexicalFile.size() - 1 ) {
        int tempLexicalFileLength = this.mLexicalFile.size() ;
        while ( this.mLexicalFile.size() == tempLexicalFileLength &&
                this.mTerminate == false ) {
          this.GetLine(); 
        } // while
        
        tempColumn = this.mCurrentColumn ;
        tempRow    = this.mCurrentRow  ;  //   Row start with -1
      } // else if   
      
      if ( this.mTerminate == false )
        return this.mLexicalFile.get( tempColumn ).GetLineList().get( tempRow + 1 ) ;
    } // try
    catch ( IndexOutOfBoundsException e ) { 
      System.out.println( "Our-C exited ..." ) ;
      System.exit( 0 ) ; //   maybe have bug 
    } // catch
    
    return null;
  } // PeekToken()
     
  public TokenInfo GetToken() { //  
    try {
      int tempColumn = this.mCurrentColumn ; //   
      int tempRow    = this.mCurrentRow ; //   
      if ( tempRow + 1 == this.mLexicalFile.get( tempColumn ).GetLineList().size() ) { 
        tempColumn++  ;   //   Jump to next Column
        tempRow = -1  ;  //   Row start with -1
      } // if     
      
      this.mCurrentColumn = tempColumn   ; //  
      this.mCurrentRow    = tempRow+1    ; //  
     
      return this.mLexicalFile.get( this.mCurrentColumn ).GetLineList().get( this.mCurrentRow ) ; 
    } // try
    catch ( IndexOutOfBoundsException e ) { //   actually no really need
    } // catch
    
    return null ;
  } // GetToken()
     

  
  public String MakeFunction(  Function ModifyFunc ) {
    if ( this.FindFunction( ModifyFunc ) == true ) {
      return "New definition of " + ModifyFunc.GetFunctionName() + "() entered ..." + "\n" ;  
    } // if
    else {
      this.mFunction.add( ModifyFunc ) ;
      return "Definition of " + ModifyFunc.GetFunctionName() + "() entered ..." + "\n" ;  
    } // else
  } // MakeFunction()
  
  public  boolean FindFunction( Function ModifyFunc  ) {
    for ( int i = 0 ; i < this.mFunction.size() ; i++  ) {  
      if ( this.mFunction.get( i ).GetFunctionName().equals(  ModifyFunc.GetFunctionName() ) == true )  {    
        this.mFunction.set( i, ModifyFunc ) ; 
        return true ;
      } // if
    } // for
    
    return false ;
  } // FindFunction()
  
  public String MakeTable5( TokenInfo token, int iDomain ) { //  add ENTRY
    if ( this.FindIden( token, token.GetValue(), 2, iDomain ) == null ) {
      G.sPsid++ ; // Entry add one
      token.SetEntry( G.sPsid );
      token.SetDomain( iDomain );
      this.mTable5.add( token ) ; 
      return "Definition of " + token.GetValue() + " entered ..."  ;
    } // if
    else {  
      return "New definition of " + token.GetValue() + " entered ..."  ;
    } // else
  } // MakeTable5()

  public TokenInfo FindIden( TokenInfo tokenInfo, String sId, int situation, int iDomain ) {  
    for ( int i = 0 ; i < this.mTable5.size() ; i++ ) {
      if ( situation == 1 ) {
        if ( this.mTable5.get( i ) != null && 
             this.mTable5.get( i ).GetValue().equals( tokenInfo.GetValue() ) == true && 
             this.mTable5.get( i ).GetDomain() == iDomain ) {
          this.mTable5.set( i, tokenInfo ) ;
          return this.mTable5.get( i ) ;
        } // if    
      } // if
      else if ( situation == 2 ) {
        if ( this.mTable5.get( i ) != null && 
             this.mTable5.get( i ).GetValue().equals( sId ) == true &&
             this.mTable5.get( i ).GetDomain() == iDomain ) {
          if ( tokenInfo != null ) {
            this.mTable5.set( i, tokenInfo ) ;
            tokenInfo.SetDomain( iDomain ) ;
          } // if
          
          return this.mTable5.get( i ) ;
        } // if      
      } // else if
    } // for 

    return null ; //   undefined Id
  } // FindIden()
 
  public void ModifyDomain( int iDomain ) {
    for ( int i = this.mTable5.size() - 1 ;  i >= 0 ; i-- ) {
      if (  this.mTable5.get( i ) != null &&  this.mTable5.get( i ).GetDomain() == iDomain ) {
        this.mTable5.remove( i ) ;
      } // if
    } // for
  } // ModifyDomain()
  
  public void ModifyAllDomain() {
    for ( int i = this.mTable5.size() - 1 ; i >= 0 ; i-- ) {
      if ( this.mTable5.get( i ) != null && this.mTable5.get( i ).GetDomain() != 1  ) {
        this.mTable5.remove( i ) ;
      } // if
    } // for
  } // ModifyAllDomain()
  
  public ArrayList<TokenInfo> BubleSort_Id( ArrayList< TokenInfo > aTemp ) {    
    for ( int j = 0 ; j < aTemp.size() ; j++ ) {            
      for ( int i = j + 1 ; i < aTemp.size() ; i++ ) {
        if ( ( aTemp.get( i ) ).GetValue().
             compareToIgnoreCase( aTemp.get( j ).GetValue( ) ) < 0 ) {                     
          TokenInfo t = aTemp.get( j ) ;
          aTemp.set( j, aTemp.get( i ) ) ;
          aTemp.set( i, t ) ;
        } // if
      } // for
    } // for
    
    return aTemp ;
  } // BubleSort_Id()
  
  public ArrayList<Function> BubleSort_Function( ArrayList< Function > aTemp ) {    
    for ( int j = 0 ; j < aTemp.size() ; j++ ) {            
      for ( int i = j + 1 ; i < aTemp.size() ; i++ ) {
        if ( ( aTemp.get( i ) ).GetFunctionName().
             compareToIgnoreCase( aTemp.get( j ).GetFunctionName( ) ) < 0 ) {                     
          Function t = aTemp.get( j ) ;
          aTemp.set( j, aTemp.get( i ) ) ;
          aTemp.set( i, t ) ;
        } // if
      } // for
    } // for  

    return aTemp ;
  } // BubleSort_Function()
  
  public void Done() {
    this.SetTerminate( true ) ;
    System.out.println( "> Our-C exited ..." ) ;
    System.exit( 0 ) ;
  } // Done() 
  
  public String PrintId( TokenInfo tempId ) {
    String opt = "" ;
    if ( tempId.GetType() == 3 ) { // int
      opt = "int " +  tempId.GetValue() + " ;" ;
    } // if
    else if ( tempId.GetType() == 4 ) { // float
      opt = "float " +  tempId.GetValue() + " ;" ;      
    } // else if
    else if ( tempId.GetType() == 6 ) { // char
      opt = "char " +  tempId.GetValue() + " ;" ;      
    } // else if
    else if ( tempId.GetType() == 7 ) { // string
      opt = "string " +  tempId.GetValue() + " ;" ;       
    } // else if 
    else if ( tempId.GetType() == 8 ) { // bool
      opt = "bool " +  tempId.GetValue() + " ;" ;       
    } // else if 
    else if ( tempId.GetType() == 9 ) { // array
      opt =  tempId.GetValue() + "[ " + tempId.GetTable7().GetSize() + " ] " ;
      if ( tempId.GetTable7().GetType() == 3 ) {
        opt = "int " + opt + ";" ;   
      } // if 
      else if ( tempId.GetTable7().GetType() == 4 ) {
        opt = "float " + opt + ";" ;   
      } // else if
      else if ( tempId.GetTable7().GetType() == 6 ) {
        opt = "char " + opt + ";" ;  
      } // else if
      else if ( tempId.GetTable7().GetType() == 7 ) {
        opt = "string " + opt + ";" ;  
      } // else if 
      else if ( tempId.GetTable7().GetType() == 8 ) {
        opt =  "bool " + opt + ";" ; 
      } // else if
    } // else if
    
    return opt + "\n" ;
  } // PrintId()
   
  
  public String PrettyPrint( Function tempFunction  ) {
    String opt = "" ;
    Stack<String> eof = new Stack<String>() ;
    eof.push( "" ) ;
    ArrayList<LineInfo> tempFile = tempFunction.GetTokenLexical().GetLexicalFile() ;
    int tempColumn = 0 ;
    int tempRow    = 0 ;             
    int tempNextColumn = 0 ;
    int tempNextRow    = 0 ;

    while ( tempColumn < tempFile.size() ) {
      if ( tempRow >= tempFile.get( tempColumn ).GetLineList().size()   ) {  
        tempColumn++ ;
        tempRow = 0  ;
      } // if
      
      tempNextRow = tempRow + 1 ;
      tempNextColumn = tempColumn ;
      
      if ( tempNextColumn < tempFile.size() && tempNextRow >=
           tempFile.get( tempNextColumn ).GetLineList().size()   ) {
        tempNextColumn++ ;
        tempNextRow = 0 ;     
      } // if 
      
      if ( tempColumn < tempFile.size()  ) {
        if ( tempNextColumn < tempFile.size()  && tempFile.get( tempNextColumn ).
             GetLineList().get( tempNextRow ).GetValue().equals( "}" ) == true   ) {
          eof.pop() ;   
        } // if
        else if ( tempNextColumn < tempFile.size()  && tempFile.get( tempNextColumn ).
                  GetLineList().get( tempNextRow ).GetValue().equals( "{" ) == true  ) {
          eof.push( eof.peek() + "  " ) ;   
        } // else if 
        
        if ( tempNextColumn < tempFile.size()  &&  tempFile.get( tempNextColumn ).
             GetLineList().get( tempNextRow ).GetValue().equals( "(" ) == true ) {       
          if ( tempFile.get( tempColumn ).GetLineList().
               get( tempRow ).GetValue().equals( "while" ) == true || 
               tempFile.get( tempColumn ).GetLineList().
               get( tempRow ).GetValue().equals( "if" ) == true  ) {
            opt = opt + tempFile.get( tempColumn ).GetLineList().get( tempRow ).GetValue() + " " ; //  
          } // if
          else {
            opt = opt + tempFile.get( tempColumn ).GetLineList().get( tempRow ).GetValue()  ; //   
          } // else 
        } // if
        else if ( tempNextColumn < tempFile.size()  &&
                  ( tempFile.get( tempNextColumn ).GetLineList().
                  get( tempNextRow ).GetValue().equals( "," ) == true ||
                    tempFile.get( tempNextColumn ).GetLineList().
                    get( tempNextRow ).GetValue().equals( "[" ) == true  ) ) {
          opt = opt + tempFile.get( tempColumn ).GetLineList().get( tempRow ).GetValue()  ; //   
        } // else if
        else if (  tempFile.get( tempColumn ).GetLineList().get( tempRow ).GetTable() == 5 && 
                  ( tempNextColumn < tempFile.size()  && 
                    ( tempFile.get( tempNextColumn ).GetLineList().
                    get( tempNextRow ).GetValue().equals( "++" ) == true ||
                      tempFile.get( tempNextColumn ).GetLineList().
                      get( tempNextRow ).GetValue().equals( "--" ) == true ) ) ) {
          opt = opt + tempFile.get( tempColumn ).GetLineList().get( tempRow ).GetValue()  ;
        } // else if 
        else if ( tempFile.get( tempColumn ).GetLineList().
                  get( tempRow ).GetValue().equals( ";" ) == true ) {
          opt = opt + tempFile.get( tempColumn ).GetLineList().get( tempRow ).GetValue() + "\n" ; // 
          opt = opt + eof.peek() ;
        } // else if 
        else if ( tempFile.get( tempColumn ).GetLineList().
                  get( tempRow ).GetValue().equals( "{" ) == true ) {
          opt = opt + tempFile.get( tempColumn ).GetLineList().get( tempRow ).GetValue() + "\n" ; // 
          opt = opt + eof.peek() ;
        } // else if
        else if ( tempFile.get( tempColumn ).GetLineList().
                  get( tempRow ).GetValue().equals( "}" ) == true ) {
          opt = opt + tempFile.get( tempColumn ).GetLineList().get( tempRow ).GetValue() + "\n" ; // 
          opt = opt + eof.peek() ;
        } // else if 
        else if ( tempFile.get( tempColumn ).GetLineList().
                  get( tempRow ).GetValue().equals( "++" ) || 
                  tempFile.get( tempColumn ).
                  GetLineList().get( tempRow ).GetValue().equals( "--" ) ) {        
          if ( tempNextColumn < tempFile.size()  && 
               tempFile.get( tempNextColumn ).GetLineList().get( tempNextRow ).GetTable() == 5   ) {
            opt = opt + tempFile.get( tempColumn ).GetLineList().get( tempRow ).GetValue() ;
          } // if      
          else {
            opt = opt + tempFile.get( tempColumn ).GetLineList().get( tempRow ).GetValue() + " " ;  
          } // else 
        } // else if
        else {
          opt = opt + tempFile.get( tempColumn ).GetLineList().get( tempRow ).GetValue() + " " ;       
        } // else    
      } // if
      
      tempRow++ ;
    } // while
    
    return opt  ;
  } // PrettyPrint() 
  
  public String ListAllVariables() {
    String opt = "" ;
    ArrayList<TokenInfo> tempArrayList = this.mTable5 ;
    tempArrayList = this.BubleSort_Id( tempArrayList ) ;
    for ( int i = 0 ; i < tempArrayList.size() ; i++ )
      opt = opt + tempArrayList.get( i ).GetValue() + "\n" ;
    
    return opt ;
  } // ListAllVariables()
  
  public String ListAllFunctions() {
    String opt = "" ;
    ArrayList<Function> tempArrayList = this.mFunction ;
    tempArrayList = this.BubleSort_Function( tempArrayList )  ;
    for ( int i = 0 ; i < tempArrayList.size() ; i++ )
      opt = opt + tempArrayList.get( i ).GetFunctionName() + "()" + "\n" ;
    return opt ;
  } // ListAllFunctions()
  
  public String ListVariable( String sId ) {
    TokenInfo noShit = null ;
    TokenInfo tempId = this.FindIden( noShit, sId, 2, 1 ) ;
    if (  tempId   != null ) {
      return ( this.PrintId( tempId ) ) ;  
    } // if
    else {
      return  null ;
    } // else
  } // ListVariable()
  
  public String ListFunction( String sFunction ) {
    Function tempFunction = null ;
    for ( int i = 0 ; i < this.mFunction.size() ; i++ )
      if ( this.mFunction.get( i ).GetFunctionName().
           equals( sFunction ) == true ) 
        tempFunction = this.mFunction.get( i ) ;
    
    if ( tempFunction != null ) {
      return ( this.PrettyPrint( tempFunction ) ) ;  
    } // if
    else {
      return null  ;
    } // else
  } // ListFunction() 
  
  
  public void ResetErrorLexicalFile() { //   
    this.mLexicalFile = new ArrayList<LineInfo>() ; //   
    this.SetCurrentColumn( 0 ) ;  //  initial current column
    this.SetCurrentRow( -1 )  ; //    initial current row
    this.SetInputColumn( 0 ) ;
  } // ResetErrorLexicalFile()
    
  public void ResetLexicalFile(  int elseLine ) { //   
    if ( this.mLexicalFile.size() -1  == this.mCurrentColumn ) {
      if ( this.mLexicalFile.get( this.mCurrentColumn ).
           GetLineList().size() - 1  == this.mCurrentRow ) {
        this.mLexicalFile = new ArrayList<LineInfo>() ; //   reset Lexical File
        this.SetCurrentColumn( 0 ) ; //  initial current column
        this.SetCurrentRow( -1 )   ; //  initial current row 
        this.SetInputColumn( 0 ) ;
      } // if
      else {
        ArrayList<TokenInfo> tempArrayList =
        this.mLexicalFile.get( this.mCurrentColumn ).GetLineList() ;
        while ( this.mCurrentRow >= 0 ) {
          tempArrayList.remove( this.mCurrentRow ) ;      
          this.mCurrentRow-- ;
        } // while 
      
        for ( int i = 0 ; i < tempArrayList.size() ; i++ ) {
          TokenInfo tempTokenInfo = tempArrayList.get( i ) ;
          if ( elseLine != 0 ) 
            tempTokenInfo.SetInputColumn( elseLine ) ; 
          else 
            tempTokenInfo.SetInputColumn( 1 ) ;
          
          tempArrayList.set(  i, tempTokenInfo ) ;
        } // for
        
        this.SetInputColumn( 1 ) ; 
        this.mLexicalFile = new ArrayList<LineInfo>() ; //   reset Lexical File
        LineInfo tempLineInfo = new LineInfo( tempArrayList ) ; //   
        this.mLexicalFile.add( tempLineInfo ) ;
        this.SetCurrentColumn( 0 ); //   set CurrentColumn
        this.SetCurrentRow( -1 );                  
      } // else
    } // if              
  } // ResetLexicalFile()
   
  public void ToLexical_output() { //   Output the lexical File
    String opt = "" ;
    for (  int i = 0 ; i < mLexicalFile.size() ; i++ ) {
      opt = opt + mLexicalFile.get( i ).ToString( "" )  ;     
    } // for      
  } // ToLexical_output()

} // class Lexical
// *****************end of Lexical Class***************//

class Table7 { // only thing about one dimension
  // 3 int 4 float 5 id 6 char 7 string 8 bool 9 array
  private int mType ; // ArrayType
  private int mSize  ; // ArraySize 
  private String[ ] mStringArray ;
  private float[  ] mFloatArray ;
  
  public Table7( int iType, int iSize ) {
    this.SetSize( iSize ) ;
    this.SetType( iType ) ;
    if ( mType == 3 || mType == 4 ) {
      // this.mFloatArray = tempFloat ;
    } // if
    else {
      // this.mStringArray = new String[ mSize ] ;
    } // else
  } // Table7()
  
  public int GetType() {
    return this.mType ;
  } // GetType()
  
  public int GetSize() {
    return this.mSize ;    
  } // GetSize() 
  
  public void SetType( int iType ) {
    this.mType = iType ;
  } // SetType()
    
  public void SetSize( int iSize ) {
    this.mSize = iSize ;  
  } // SetSize() 
    
  public String GetStringArray( int index ) {
    return this.mStringArray[ index ] ;
  } // GetStringArray()
  
  public float GetFloatArray( int index ) {
    return this.mFloatArray[ index ] ; 
  } // GetFloatArray()
  
  public void SetArray( String sArrayString, int iIndex, int iType ) {
    if ( iType == 3 || iType == 4 ) {
      this.mFloatArray[ iIndex ] = Float.valueOf( sArrayString ) ;    
    } // if 
    else {
      this.mStringArray[ iIndex ] = sArrayString  ;
    } // else
  } // SetArray()
  
} // class Table7
// ***********************************start of Table7 ******************************//

// ***********************************end of Table7 ********************************//


// ***********************************start of Function Class***********************//
class Function {
  private Lexical mTokenLexical ; // Function LexicalFile
  private Lexical mOgTokenLexical ;
  
  private String mFunctionType ; // which type of function definition
  private String mFunctionName ;
  private String mOutputValue = "" ; //   printout  Final Value 
  
  // private TokenInfo mReturnValue = null ; // Store Return Value
  
  private ArrayList<TokenInfo> mCallTable5 = new ArrayList<TokenInfo>() ; 
  
  private Function mTempFunction = null ;
  
  private boolean mIsMain ; // it's main or not
  private boolean mCorrectStatement ;// Do the Function Computation
  private boolean mIsReturn = false  ;
  
  private String[] mErrorType = { "unrecognized token with first char : ",
                                  "unexpected token : ",
                                  "undefined identifier : " } ; 
  
  
  private int mPastColumn, mPastRow ;
  private int mDomain ;
  private int mErrorNum ;
  private int mIfLine ;
  
  public Lexical GetTokenLexical() {
    return this.mTokenLexical ;    
  } // GetTokenLexical()
  
  public String GetFunctionType() {
    return this.mFunctionType ;    
  } // GetFunctionType()
   
  public ArrayList<TokenInfo> GetCallTable5() {
    return this.mCallTable5 ;    
  } // GetCallTable5()
  
  
  public boolean GetIsMain() {
    return this.mIsMain ;    
  } // GetIsMain()
  
  public boolean GetCorrectStatement() {
    return this.mCorrectStatement ;    
  } // GetCorrectStatement()
  
  public String GetFunctionName() {
    return this.mFunctionName ;    
  } // GetFunctionName()

  public void SetTokenLexical( Lexical oTokenLexical ) {
    this.mTokenLexical = oTokenLexical ;      
  } // SetTokenLexical()
    
  public void SetFunctionType( String sFunctionType ) {
    this.mFunctionType = sFunctionType ;
  } // SetFunctionType()
    
    
  public void SetCallTable5( ArrayList<TokenInfo> aCallTokenInfo ) {
    this.mCallTable5 = aCallTokenInfo ;    
  } // SetCallTable5()
        
  public void SetIsMain( boolean bIsMain ) {
    this.mIsMain = bIsMain ;    
  } // SetIsMain()
  
  public void SetCorrectStatement( boolean bCorrectStatement ) {
    this.mCorrectStatement = bCorrectStatement ;
  } // SetCorrectStatement()
  
  public void SetFunctionName( String sFunctionName ) {
    this.mFunctionName = sFunctionName ;     
  } // SetFunctionName()
  // ************************************************************************************//
  
  Function() {    
  } // Function()
  
  Function( String sFunctionType ) {
    this.SetIsMain( false ) ;
    this.mDomain = 2 ;
    this.SetFunctionType( sFunctionType ) ;
    this.mTokenLexical = new Lexical() ;
  } // Function()
  
  Function( Lexical TokenLexical, ArrayList<LineInfo> aLexicalFile,
            boolean bIsMain, String sFunctionName, String sFunctionType ) { // Constructor 1
    this.SetIsMain( bIsMain ) ;
    if (  this.GetIsMain() == true ) {
      this.mTokenLexical = TokenLexical  ;   
      this.mOgTokenLexical = TokenLexical ;
      this.SetFunctionName( sFunctionName ) ;
      this.SetFunctionType( sFunctionType ) ;
      this.mCorrectStatement = false ;
      this.mDomain = 1 ;
      this.mErrorNum = 0 ;
      this.mIfLine = 0 ;
    } // if 
    else { // Not Necessary
      this.mTokenLexical = new Lexical() ;
      this.mTokenLexical.SetLexicalFile( aLexicalFile ) ;
      this.SetFunctionName( sFunctionName ) ;
      this.SetFunctionType( sFunctionType ) ;
      this.mCorrectStatement = true ;
    } // else
  } // Function()
  

  
  private ArrayList<LineInfo> MakeFunctionLineInfo( ) {
    ArrayList<LineInfo> aTempLineInfo = this.mTokenLexical.GetLexicalFile() ;  
    ArrayList<TokenInfo> aTempTokenInfo = new ArrayList<TokenInfo>() ;
    int i = 0 ;
     // **********Copy last Column**********//
    while ( i <= this.mTokenLexical.GetCurrentRow()  ) {
      TokenInfo tempTokenInfo = aTempLineInfo.get( this.mTokenLexical.GetCurrentColumn() ).
      GetLineList().get( i ) ;
      aTempTokenInfo.add( tempTokenInfo ) ;
      i++ ;
    } // while
    
    LineInfo oTempLineInfo = new LineInfo( aTempTokenInfo ) ;
    // ****************************************//
    // Different between LexicalFile and pretty print output
    int countDelete = aTempLineInfo.size() - this.mTokenLexical.GetCurrentColumn()  ;
    while ( countDelete > 0  ) {
      aTempLineInfo.remove( aTempLineInfo.size() - 1  )  ;
      countDelete-- ;
    } // while
    
    aTempLineInfo.add( oTempLineInfo ) ;
    return aTempLineInfo ;
  } // MakeFunctionLineInfo()
  
  private void WhichError( TokenInfo tempTokenInfo, int Line ) { // 
    if (  this.mErrorNum == 0 ) {
      if ( tempTokenInfo.GetTable() == 0 && tempTokenInfo.GetEntry() == 0 ) { // 
        System.out.print( "> Line " + tempTokenInfo.GetInputColumn() + " : " ) ;
        System.out.println( this.mErrorType[0] +
                            "'" + tempTokenInfo.GetValue().charAt( 0 ) + "'" ) ;
        // System.out.println( "ErrorLine-->" + Line ) ;   
      } // if
      else { // 
        System.out.print( "> Line " + tempTokenInfo.GetInputColumn() + " : " ) ;
        System.out.println( this.mErrorType[1] + "'" + tempTokenInfo.GetValue() + "'" ) ; 
        // System.out.println( "ErrorLine-->" + Line );
      } // else
    } // if
  
    this.mErrorNum++ ;
  } // WhichError()
  
  public boolean Run() {
    this.mTempFunction = null ;
    this.mErrorNum = 0 ;
    if ( this.User_Input() == true ) {
      this.mTokenLexical.ResetLexicalFile( this.mIfLine ) ; 
      return true ;
    } // if
    else {
      this.mTokenLexical.ResetErrorLexicalFile() ;
      this.mTokenLexical.ModifyAllDomain() ;
      return false ;
    } // else
  } // Run()
  
  private boolean User_Input() {
    if ( this.mErrorNum == 0 &&  this.Definition() == true ) {
      // Definition of "Function Name" entered 
      System.out.print( "> " + this.mOutputValue ) ;
      return true ;
    } // if
    else if (  this.mErrorNum == 0 && this.Statement(  ) == true ) {
      // Statement executed     
      this.mOutputValue = "> " + this.mOutputValue + "Statement executed ..." + "\n" ;
      System.out.print( this.mOutputValue ) ;
      return true ;
    } // else if
  
    return false ;
  } // User_Input()
  
  private boolean Definition() { // run the functon definition
    if ( this.mTokenLexical.PeekToken().GetTable() == 2 &&
         this.mTokenLexical.PeekToken().GetEntry() == 6 ) { // 
      String tempFunctionType = this.mTokenLexical.GetToken().GetValue() ; // 
      // add Function to new Function and GetToken
      if ( this.mTokenLexical.PeekToken().GetTable() == 5 ) {
        String tempFunctionName = this.mTokenLexical.GetToken().GetValue() ; //
        if ( this.Function_definition_without_ID( tempFunctionType, tempFunctionName ) == true ) {
          return true ;
        } // if
        else { // error Function_definition_without_ID
          return false ;
        } // else
      } // if
      else { // error Id
        this.WhichError( this.mTokenLexical.PeekToken(), 1276 ) ; 
        return false ;
      } // else
    } // if
    else if ( this.Type_Specifier() == true ) { // other type of function
      String tempFunctionType = this.mTokenLexical.GetToken().GetValue() ; //
      // add Function to new Function and GetToken
      if ( this.mTokenLexical.PeekToken().GetTable() == 5 ) {
        String tempFunctionName = this.mTokenLexical.GetToken().GetValue() ; // 
        if ( this.Function_definition_or_declarators( tempFunctionType, tempFunctionName )  == true ) {
          return true ;  
        } // if
        else {
          return false ;
        } // else
      } // if
      else {
        this.WhichError( this.mTokenLexical.PeekToken(), 1293 ) ;
        return false ; 
      } // else
    } // else if
  
    return false ; // not the Real false
  } // Definition()
  
  private boolean ListAll_Or_Done( int VarOrFunc ) { // 0 is Done 1 is Variable 2 is Function 
    this.mTokenLexical.GetToken() ; // Skip "ListAll"
    String tempPrintString = null ;
    if ( this.mTokenLexical.PeekToken().GetTable() == 1 &&
         this.mTokenLexical.PeekToken().GetEntry() == 6 ) {
      // it's '('
      this.mTokenLexical.GetToken() ; // Skip '('
      if ( this.mTokenLexical.PeekToken().GetTable() == 7 &&
           this.mTokenLexical.PeekToken().GetEntry() == 1 ) { // it's '"'
        if ( VarOrFunc != 0 ) {
          this.mTokenLexical.GetToken() ; // Skip '"'
          if ( this.mTokenLexical.PeekToken().GetTable() == 7 ) {
            TokenInfo tempId = this.mTokenLexical.GetToken() ; // Skip Id
            if ( VarOrFunc == 1 ) {
              // System.out.println( tempId.GetValue()  ) ;
              tempPrintString = mOgTokenLexical.ListVariable( tempId.GetValue() ) ; 
            } // if
            else if ( VarOrFunc == 2 ) {
              tempPrintString = mOgTokenLexical.ListFunction( tempId.GetValue()  ) ; 
            } // else if
            
            if ( this.mTokenLexical.PeekToken().GetTable() == 7 && 
                 this.mTokenLexical.PeekToken().GetEntry() == 1 ) { // it's '"'
              this.mTokenLexical.GetToken() ; // Skip '"'
              if ( tempPrintString != null  ) {                           
              } // if
              else { // error Undefine ID
                if ( this.mErrorNum ==  0 ) {
                  System.out.print( "> Line " + tempId.GetInputColumn() + " : " ) ;
                  System.out.println( this.mErrorType[2] + "'" + tempId.GetValue() + "'" ) ;
                  this.mErrorNum++ ;
                  // System.out.println( "ErrorLine-->" + 1348 ) ;
                } // if
              
                return false ;
              } // else
            } // if 
            else { // error '"'
              this.WhichError( this.mTokenLexical.GetToken(), 1331 ) ;
              return false ;
            } // else
          } // if
          else { // error ID
            this.WhichError( this.mTokenLexical.PeekToken(), 1336  ) ;
            return false ;
          } // else         
        } // if
        else { // VarOrFunc is 0 ERROR Done()
          this.WhichError( this.mTokenLexical.PeekToken(), 1341 ) ;
          return false ;
        } // else
      } // if
        
      if ( this.mTokenLexical.PeekToken().GetTable() == 1 && 
           this.mTokenLexical.PeekToken().GetEntry() == 7  ) {
        this.mTokenLexical.GetToken(); // Skip ')'
        if ( this.mTokenLexical.PeekToken().GetTable() == 1 &&
             this.mTokenLexical.PeekToken().GetEntry() == 5 ) {
          this.mTokenLexical.GetToken() ; // Skip ';'
          if ( VarOrFunc == 1 && this.mDomain == 1 ) {
            if ( tempPrintString == null ) {
              this.mOgTokenLexical.SetTable5( this.mTokenLexical.GetTable5() );
              this.mOutputValue = this.mOutputValue + mOgTokenLexical.ListAllVariables() ; // 
            } // if
            else {
              this.mOutputValue = this.mOutputValue + tempPrintString ;  // 
            } // else
          } // if 
          else if ( VarOrFunc == 2 && this.mDomain == 1 ) {
            if ( tempPrintString == null )
              this.mOutputValue = this.mOutputValue + mOgTokenLexical.ListAllFunctions() ; // 
            else
              this.mOutputValue = this.mOutputValue + tempPrintString ;  // 
          } // else if
          else if ( VarOrFunc == 0  ) {
            mOgTokenLexical.Done() ; // Program Exit
          } // else if
            
          return true ;
        } // if
        else { // error ';'
          this.WhichError( this.mTokenLexical.PeekToken(), 1375 ) ;
          return false ;
        } // else
      } // if
      else { // error ')'
        this.WhichError( this.mTokenLexical.PeekToken(), 1377 ) ;
        return false ;
      } // else
    } // if
    else { // error '('
      this.WhichError( this.mTokenLexical.PeekToken(), 1382 ) ;
      return false ;
    } // else
  } // ListAll_Or_Done()
  
  private boolean CheckCINId( TokenInfo tempId ) {   
    int count = 0 ;
    for ( int i =  this.mTokenLexical.GetTable5().size() - 1 ; i >= 0  ; i-- ) {
      if ( this.mTokenLexical.GetTable5().get( i ) != null && 
           this.mTokenLexical.GetTable5().get( i ).GetValue().
           equals( tempId.GetValue() ) == true ) {
        tempId.SetType( this.mTokenLexical.GetTable5().get( i ).GetType() );
        i = -1 ;
        count++ ;
      } // if    
    } // for 
    
    if (  count != 0 ) {
      // System.out.println( tempId.GetType() );
      if ( tempId.GetType() != 9 ) { // Not Array
        return true ;  
      } // if
      else { // It's Array
        if ( this.mTokenLexical.PeekToken().GetTable() == 1 && 
             this.mTokenLexical.PeekToken().GetEntry() == 15 ) {
          this.mTokenLexical.GetToken() ; // Skip '['
          if ( this.Expression() == true ) {
            if ( this.mTokenLexical.PeekToken().GetTable() == 1 && 
                 this.mTokenLexical.PeekToken().GetEntry() == 16 ) {
              this.mTokenLexical.GetToken() ; // Skip ']'
              return true ;
            } // if
            else { // error ']'
              this.WhichError( this.mTokenLexical.PeekToken(), 1413 ) ;           
              return false ;
            } // else
          } // if
          else { // error Expression
            return false ;
          } // else 
        } // if

        return true ;
      } // else  
    } // if
    else {
      return false ;  
    } // else  
  } // CheckCINId()
  

  
  
  private boolean Cin() {
    this.mTokenLexical.GetToken() ; // Skip Cin 
    int count = 0 ;
    while ( this.mTokenLexical.PeekToken().GetTable() == 1 && 
            this.mTokenLexical.PeekToken().GetEntry() == 35 ) {
      this.mTokenLexical.GetToken() ; // Skip '>>'
      if ( this.mTokenLexical.PeekToken().GetTable() == 5 ) {
        TokenInfo tempId = this.mTokenLexical.GetToken() ; // Skip Id
        if ( this.CheckCINId( tempId ) == true ) {
          // keep Doing  
          count++ ;
        } // if
        else { // error CheckCINId
          if ( this.mErrorNum ==  0 ) {
            System.out.print( "> Line " + tempId.GetInputColumn() + " : " ) ;
            System.out.println(  this.mErrorType[2] + "'" + tempId.GetValue() + "'" ) ;
            this.mErrorNum++ ;
            // System.out.println( "ErrorLine-->" + 1473 ) ;
          } // if
          
          return false ;
        } // else
      } // if
      else { // error ID
        this.WhichError( this.mTokenLexical.PeekToken(), 1455 ) ;     
        return false ;  
      } // else
    } // while
    
    
    if ( count != 0 ) {
      if ( this.mTokenLexical.PeekToken().GetTable() == 1  &&
           this.mTokenLexical.PeekToken().GetEntry() == 5 ) {
        this.mTokenLexical.GetToken() ; // Skip ';'
        return true ;
      } // if
      else { // error ';'
        this.WhichError( this.mTokenLexical.PeekToken(), 1468 ) ;
        return false ;    
      } // else
    } // if
    else { // error first '>>'
      this.WhichError( this.mTokenLexical.PeekToken(), 1473 ) ;
      return false ;  
    } // else
  } // Cin()
  
  private boolean Cout() {
    this.mTokenLexical.GetToken() ; // Skip Cout
    int count = 0 ;
    while ( this.mTokenLexical.PeekToken().GetTable() == 1 && 
            this.mTokenLexical.PeekToken().GetEntry() == 36 ) {
      this.mTokenLexical.GetToken() ; // Skip '<<'
      if ( this.Expression() == true ) { // it's " is String
        count++ ;
      } // if
      else { // error Expression
        return false ;
      } // else
    } // while()
  
    if ( count != 0  ) {
      if ( this.mTokenLexical.PeekToken().GetTable() == 1  &&
           this.mTokenLexical.PeekToken().GetEntry() == 5 ) {
        this.mTokenLexical.GetToken() ; // Skip ';'
        return true ;
      } // if
      else { // error ';'
        this.WhichError( this.mTokenLexical.PeekToken(), 1500 ) ;
        return false ;    
      } // else
    } // if
    else { // error first '<<'
      this.WhichError( this.mTokenLexical.PeekToken(), 1505 ) ;
      return false ;
    } // else
  } // Cout()
  

  private boolean Statement(  ) {
    // ************Project2 Boss Hsiah Statement***************//
    if ( this.mErrorNum == 0 && 
         this.mTokenLexical.PeekToken().GetTable() == 5 &&  
         this.mTokenLexical.PeekToken().GetValue().equals( "ListAllVariables" ) == true ) { 
      if ( this.ListAll_Or_Done( 1 ) == true ) {
        return true ;
      } // if
      else {
        return false ;
      } // else
    } // if
    else if ( this.mErrorNum == 0 && 
              this.mTokenLexical.PeekToken().GetTable() == 5 &&  
              this.mTokenLexical.PeekToken().GetValue().equals( "ListAllFunctions" ) == true  ) {
      if ( this.ListAll_Or_Done( 2  ) == true ) {
        return true ;
      } // if
      else {
        return false ;
      } // else 
    } // else if
    else if ( this.mErrorNum == 0 &&  
              this.mTokenLexical.PeekToken().GetTable() == 5 &&  
              this.mTokenLexical.PeekToken().GetValue().equals( "ListVariable" ) == true  ) {
      if ( this.ListAll_Or_Done( 1  ) == true ) {
        return true ;
      } // if
      else {
        return false ;
      } // else 
    } // else if
    else if ( this.mErrorNum == 0 &&  
              this.mTokenLexical.PeekToken().GetTable() == 5 &&  
              this.mTokenLexical.PeekToken().GetValue().equals( "ListFunction" ) == true  ) {
      if ( this.ListAll_Or_Done( 2  ) == true ) {
        return true ;
      } // if
      else {
        return false ;
      } // else 
    } // else if
    else if ( this.mErrorNum == 0 && 
              this.mTokenLexical.PeekToken().GetTable() == 5 &&  
              this.mTokenLexical.PeekToken().GetValue().equals( "Done" ) == true  ) {
      if ( this.ListAll_Or_Done( 0  ) == true ) {
        return true ;
      } // if
      else {
        return false ;
      } // else 
    } // else if
    else if ( this.mErrorNum == 0 && 
              this.mTokenLexical.PeekToken().GetTable() == 5 &&  
              this.mTokenLexical.PeekToken().GetValue().equals( "cin" ) == true  ) {  
      if ( this.Cin() == true ) {
        return true ;
      } // if
      else {
        return false ;
      } // else
    } // else if 
    else if ( this.mErrorNum == 0 && 
              this.mTokenLexical.PeekToken().GetTable() == 5 &&  
              this.mTokenLexical.PeekToken().GetValue().equals( "cout" ) == true  ) {
      if ( this.Cout() == true ) {
        return true ;
      } // if
      else {
        return false ;
      } // else
    } // else if 
    else if ( this.mErrorNum == 0 &&  this.mTokenLexical.PeekToken().GetTable() == 1 && 
              this.mTokenLexical.PeekToken().GetEntry() == 5  ) { // only ';'
      this.mTokenLexical.GetToken() ; // skip ';'
      return true ;  
    } // else if
    else if ( this.mErrorNum == 0 && this.mTokenLexical.PeekToken().GetTable() == 2 &&
              this.mTokenLexical.PeekToken().GetEntry() == 11  ) {
      // Return Function   
      this.mIsReturn = true ;
      this.mTokenLexical.GetToken() ; // Skip Return
      this.Expression() ; // false still keep Doing
      if ( this.mErrorNum != 0 ) return false ;
      
      if ( this.mTokenLexical.PeekToken().GetTable() == 1 &&
           this.mTokenLexical.PeekToken().GetEntry() == 5 ) {
        return true ;  
      } // if
      else { // error ";"
        this.WhichError( this.mTokenLexical.PeekToken(), 1593 ) ;
        return false ;
      } // else
    } // else if 
    else if ( this.mErrorNum == 0 && this.mTokenLexical.PeekToken().GetTable() == 2 &&
              this.mTokenLexical.PeekToken().GetEntry() == 7  ) { // if statement
      this.mTokenLexical.GetToken() ; // Skip If
      if ( this.mTokenLexical.PeekToken().GetTable() == 1 &&
           this.mTokenLexical.PeekToken().GetEntry() == 6  ) { // it's '('
        this.mTokenLexical.GetToken() ; // Skip '(' 
        if ( this.Expression( ) == true ) {
          if ( this.mTokenLexical.PeekToken().GetTable() == 1 &&
               this.mTokenLexical.PeekToken().GetEntry() == 7   ) { // ')'
            this.mTokenLexical.GetToken() ; // Skip ')'
            if ( this.Statement( )  == true ) {
              int beforeElseLine = this.mTokenLexical.GetInputColumn() ;
              if ( this.mTokenLexical.PeekToken().GetTable() == 2 &&
                   this.mTokenLexical.PeekToken().GetEntry() == 8 ) {
                // it's else statement  
                this.mTokenLexical.GetToken() ; // Skip else
                       
                if ( this.Statement(  ) == true ) { // else statement
                  return true ; // True else statement
                } // if
                else {
                  return false ; // error else statement  
                } // else
              } // if
              else {
                this.mIfLine = this.mTokenLexical.GetInputColumn() - beforeElseLine ; 
                return true ; // only If statement
              } // else
            } // if 
            else {
              return false ; // error if statement
            } // else
          } // if
          else { // error ')'
            this.WhichError( this.mTokenLexical.PeekToken(), 1630 ) ;
            return false ;  
          } // else
        } // if
        else { // error Expression
          return false ;
        } // else 
      } // if
      else { // error '('
        this.WhichError( this.mTokenLexical.PeekToken(), 1639 ) ;
        return false ;  
      } // else
    } // else if()
    else if ( this.mErrorNum == 0 &&  this.mTokenLexical.PeekToken().GetTable() == 2 &&
              this.mTokenLexical.PeekToken().GetEntry() == 9 ) { // while statement
      this.mTokenLexical.GetToken() ; // Skip While
      if ( this.mTokenLexical.PeekToken().GetTable() == 1 && 
           this.mTokenLexical.PeekToken().GetEntry() == 6 ) { // it's '('
        this.mTokenLexical.GetToken() ; // Skip '('    
        if (  this.Expression() == true ) {        
          if ( this.mTokenLexical.PeekToken().GetTable() == 1 &&
               this.mTokenLexical.PeekToken().GetEntry() == 7 ) { // it's ')'
            this.mTokenLexical.GetToken() ; // Skip ')'  
            if ( this.Statement(  ) == true ) {
              return true ;
            } // if
            else { // error Statement
              return false ;  
            } // else
          } // if
          else {
            this.WhichError( this.mTokenLexical.PeekToken(), 1658 ) ;            
            return false ; // error ')'
          } // else
        } // if
        else {
          return false ; // error while Expression
        } // else
      } // if
      else {
        this.WhichError( this.mTokenLexical.PeekToken(), 1667 ) ;      
        return false ; // error '('
      } // else 
    } // else if 
    else if ( this.mErrorNum == 0 && this.Compound_Statement( ) == true ) {
      return true ;  
    } // else if
    else if (   this.mErrorNum == 0 && this.Expression( ) == true ) {
      if ( this.mTokenLexical.PeekToken().GetTable() == 1 &&
           this.mTokenLexical.PeekToken().GetEntry() == 5 ) {
        this.mTokenLexical.GetToken() ; // Skip ';'
        return true ;  
      } // if
      else {
        this.WhichError( this.mTokenLexical.PeekToken(), 1681 ) ;
        return false ;
      } // else
    } // else if
    
    return false ;
  } // Statement()
  
  private boolean Compound_Statement(   ) {
    if ( mErrorNum == 0 && this.mTokenLexical.PeekToken().GetTable() == 1 && 
         this.mTokenLexical.PeekToken().GetEntry() == 17 ) {
      // it's '{'
      // **************Domain***********************//
      this.mDomain++ ; // Domain Plus one
      // *******************************************//
      this.mTokenLexical.GetToken() ; // skip '{'
      boolean stop = false ;
      while (  stop == false ) {
        if ( this.mTokenLexical.PeekToken().GetTable() == 1 &&
             this.mTokenLexical.PeekToken().GetEntry() == 18 ) {
          stop = true ;  
        } // if
        else if ( this.Declaration(  ) == true )  {
          stop = false ;
        } // else if
        else if ( this.Statement( ) == true ) {
          stop = false ;
        } // else if
        else {
          // System.out.println(  "False Compound S or D " + 
          // this.mTokenLexical.PeekToken().GetValue() ) ;
          return false ;
        } // else
      } // while
      
      if ( this.mTokenLexical.PeekToken().GetTable() == 1 &&
           this.mTokenLexical.PeekToken().GetEntry() == 18 ) {       
        // it's "}"  
        this.mTokenLexical.GetToken() ; // skip '}'
        // *************Domain*********************//
        this.mTokenLexical.ModifyDomain( this.mDomain ) ;
        this.mDomain-- ;
        // ****************************************//
        return true ;
      } // if
      else {
        this.WhichError( this.mTokenLexical.PeekToken(), 1724 ) ;       
        return false ;
      } // else
    } // if
    else { // error "{"
      // this.WhichError( this.mTokenLexical.PeekToken(), 1729 ) ; 
      return false ;    
    } // else 
  } // Compound_Statement()
  
  private boolean Declaration( ) {
    if (  mErrorNum == 0 && this.Type_Specifier() == true ) { //
      String tempType = this.mTokenLexical.PeekToken().GetValue() ; // Set Type
      this.mTokenLexical.GetToken() ; // Skip Type
      if ( this.mTokenLexical.PeekToken().GetTable() == 5 ) { // it's id
        String tempName = this.mTokenLexical.PeekToken().GetValue() ; // Set Name 
        this.mTokenLexical.GetToken() ; // Skip Id
        if ( this.Rest_Of_Declarators( tempType, tempName ) == true ) {
          return true ;
        } // if
        else {
          return false ; // error Rest_Of_Declares
        } // else 
      } // if
      else {
        this.WhichError( this.mTokenLexical.PeekToken(), 1755 ) ;
        return false ; // error Id
      } // else 
    } // if 
  
    // this.WhichError( this.mTokenLexical.PeekToken(), 1760 ) ;
    return false ;
  } // Declaration()
  
  
  private boolean Expression(  ) { // be careful of return value
    if ( mErrorNum == 0 && this.Basic_Expression(  ) == true ) {
      while ( this.mTokenLexical.PeekToken().GetTable() == 1 && 
              this.mTokenLexical.PeekToken().GetEntry() == 37 ) {
        // it's ',' keep doing Basic_Expression
        this.mTokenLexical.GetToken() ; // Skip ','
        if ( this.Basic_Expression(  ) == true ) {      
        } // if
        else {
          return false ;
        } // else
      } // while
    
      // System.out.println( "Enter Basic_Exp " 
      // + this.mTokenLexical.PeekToken().GetValue() );
      return true ; // only one Basic_Expression
    } // if
    else {
      return false ;
    } // else
  } // Expression()
  
  private TokenInfo CheckId( TokenInfo tempId ) { // Check the Id is correct
    for ( int i = this.mTokenLexical.GetTable5().size() - 1 ; i >= 0 ; i-- ) {
      if ( this.mTokenLexical.GetTable5().get( i ) != null && 
           this.mTokenLexical.GetTable5().get( i ).GetValue().
           equals( tempId.GetValue() ) == true ) {
        return this.mTokenLexical.GetTable5().get( i ) ;
      } // if    
    } // for 
    
    return null ;
  } // CheckId()
  
  private boolean CheckFuncId( String FuncName ) {
    for ( int i = 0 ;  i < this.mOgTokenLexical.GetFunction().size() ; i++ ) {
      // System.out.println( "Function Name " +  
      // this.mOgTokenLexical.GetFunction().get(i).GetFunctionName()  ) ;
      if ( this.mOgTokenLexical.GetFunction().get( i ) != null && this.mOgTokenLexical.GetFunction().
           get( i ).GetFunctionName().equals( FuncName ) == true  ) {
        return true ; 
      } // if
    } // for
    
    return false ;
  } // CheckFuncId() 
  
  private boolean Basic_Expression(  ) {
    int errorIdLine = 0 ;
    if ( mErrorNum == 0 && this.mTokenLexical.PeekToken().GetTable() == 5  ) { // it's id
      // Check the Id is Define or not store Value
      // System.out.println( "Enter Basic_Exp id " 
      // + this.mTokenLexical.PeekToken().GetValue() ) ;   	
      TokenInfo tempId = CheckId( this.mTokenLexical.PeekToken() ) ;
      String errorId = this.mTokenLexical.PeekToken().GetValue() ;
      String funcName = this.mTokenLexical.PeekToken().GetValue() ;
      if ( tempId == null && this.CheckFuncId( funcName ) == false ) {
        // System.out.print( "> Line " + errorIdLine + " : " ) ;
        // System.out.println( this.mErrorType[2] + "'" + errorId + "'" ) ;
        this.mErrorNum++ ;
        errorIdLine = this.mTokenLexical.PeekToken().GetInputColumn() ;
        // System.out.println( "ErrorLine-->" + 2227 ) ;
        // return false ;  
      } // if
      
      this.mTokenLexical.GetToken() ; // skip id
      if ( this.Rest_Of_Identifier_Started_Basic_Exp( tempId, errorIdLine, errorId, funcName ) == true ) {
        return true ;
      } // if
      else { // error Rest_Of_Identifier_started_Basic_Exp
        // System.out.println(  "Fail Rest_Of_Id " + 
        // this.mTokenLexical.PeekToken().GetValue()  );
        return false ;   
      } // else
    } // if
    else if ( mErrorNum == 0 &&  this.mTokenLexical.PeekToken().GetTable() == 1  && 
              ( this.mTokenLexical.PeekToken().GetEntry() == 33 ||
                this.mTokenLexical.PeekToken().GetEntry() == 34 ) ) { 
      TokenInfo tempPPPMM = this.mTokenLexical.PeekToken() ; // Get the PP or MM
      this.mTokenLexical.GetToken() ; // Skip PPMM 
      if ( this.mTokenLexical.PeekToken().GetTable() == 5  ) {
        TokenInfo tempId = CheckId( this.mTokenLexical.PeekToken() ) ;
        String errorId = this.mTokenLexical.PeekToken().GetValue() ;

        if ( this.mErrorNum ==  0 && tempId == null ) {
          // System.out.print( "> Line " + errorIdLine + " : " ) ;
          // System.out.println( this.mErrorType[2] + "'" + errorId + "'" ) ;
          this.mErrorNum++ ;
          errorIdLine = this.mTokenLexical.PeekToken().GetInputColumn() ;
          // System.out.println( "ErrorLine-->" + 1861 ) ;
          // return false ;
        } // if
        
        this.mTokenLexical.GetToken() ; // skip Id        
        if (  this.Rest_Of_PPMM_Identifier_Started_Basic_Exp( errorId, tempId, errorIdLine ) == true ) {
          return true ;
        } // if 
        else { // error Rest_Of_PPMM
          return false ;
        } // else
      } // if
      else { // error Id
        this.WhichError( this.mTokenLexical.PeekToken(), 1838 ) ;
        return false ;
      } // else
    } // else if
    else if ( mErrorNum == 0 &&  this.mTokenLexical.PeekToken().GetTable() == 1  && 
              ( this.mTokenLexical.PeekToken().GetEntry() == 9 ||
                this.mTokenLexical.PeekToken().GetEntry() == 10 ||
                this.mTokenLexical.PeekToken().GetEntry() == 25 ) ) { // ???
      TokenInfo tempSign = this.mTokenLexical.PeekToken() ; // Get the Sign
      this.mTokenLexical.GetToken() ; // skip sign
      while ( this.mTokenLexical.PeekToken().GetTable() == 1 && 
              ( this.mTokenLexical.PeekToken().GetEntry() == 9  || 
                this.mTokenLexical.PeekToken().GetEntry() == 10  || 
                this.mTokenLexical.PeekToken().GetEntry() == 25  ) ) {
        tempSign = this.mTokenLexical.PeekToken() ; // Get the Sign 
        this.mTokenLexical.GetToken() ; // Skip sign 
      } // while
      
      if ( this.Signed_Unary_Exp( false  ) == true ) {
        if ( this.Romce_And_Romloe() == true ) {
          return true ;
        } // if
        else { // error Romce_And_Romloe
          return false ;  
        } // else
      } // if
      else { // error  Signed_Unary_Exp
        return false ;
      } // else 
    } // else if
    else if ( mErrorNum == 0 &&  this.mTokenLexical.PeekToken().GetTable() == 3 ||
              this.mTokenLexical.PeekToken().GetTable() == 4 || 
              this.mTokenLexical.PeekToken().GetTable() == 6 || 
              this.mTokenLexical.PeekToken().GetTable() == 7 ||
              this.mTokenLexical.PeekToken().GetTable() == 8 ) { // It's Constant
      TokenInfo tempTokenInfo = this.mTokenLexical.PeekToken() ;
      this.mTokenLexical.GetToken() ; // Skip Constant
      if ( tempTokenInfo.GetTable() == 7  ) {
        this.mTokenLexical.GetToken() ; // Skip Constant 
        this.mTokenLexical.GetToken() ; // Skip Constant
      } // if
      
      if ( this.Romce_And_Romloe() == true ) {
        return true ;
      } // if
      else {
        return false ;
      } // else
    } // else if
    else if ( mErrorNum == 0 &&  this.mTokenLexical.PeekToken().GetTable() == 1 &&
              this.mTokenLexical.PeekToken().GetEntry() == 6 ) { // it's '('
      this.mTokenLexical.GetToken() ; // Skip '('
      // System.out.println( "Enter () Expression " 
      // + this.mTokenLexical.PeekToken().GetValue() );
      if ( this.Expression( ) == true ) {
        // System.out.println( "Enter Correct () Expression " + 
        // this.mTokenLexical.PeekToken().GetValue() );
        if ( this.mTokenLexical.PeekToken().GetTable() == 1 &&
             this.mTokenLexical.PeekToken().GetEntry() == 7 ) { // it's ')'
          this.mTokenLexical.GetToken() ; // Skip ')'
          if ( this.Romce_And_Romloe() == true ) {
            return true ;
          } // if
          else { // error ROMCE AND ROMLOE
            return false ; 
          } // else
        } // if
        else { // error ')'
          this.WhichError( this.mTokenLexical.PeekToken(), 1897 ) ;
          return false ; 
        } // else
      } // if
      else { // error exp
        return false ;
      } // else
    } // else if    
    
    // System.out.println( "Enter Basic Fail " + 
    // this.mTokenLexical.PeekToken().GetValue() ) ;
    if ( mErrorNum == 0 && this.mTokenLexical.PeekToken().GetValue().equals( ";" ) == true )
      return true ;
    else
      this.WhichError( this.mTokenLexical.PeekToken(), 1950 ) ;
    return false ;
  } // Basic_Expression()
  
  
  private boolean Type_Specifier() {
    if ( this.mTokenLexical.PeekToken().GetTable() == 2  && 
         this.mTokenLexical.PeekToken().GetEntry() >= 1  &&
         this.mTokenLexical.PeekToken().GetEntry() <= 5 ) {
      return true ;
    } // if
    
    return false ;
  } // Type_Specifier()
  
  private int Return_Type_Specifier( String tempString ) {
    if ( tempString.equals( "int" ) ) 
      return 3  ;
    if ( tempString.equals( "float" ) ) 
      return 4  ;
    if ( tempString.equals( "char" ) )
      return 6  ;
    if ( tempString.equals( "string" ) )
      return 7  ;
    if ( tempString.equals( "bool" ) )
      return 8  ;
    
    return -1 ;
  } // Return_Type_Specifier()
  
  
  private boolean Function_definition_or_declarators( String tempType, String tempName ) {
    if ( this.Function_definition_without_ID( tempType, tempName ) == true ) {
    // this.mOutputValue = this.mOutputValue + " Definition of " 
    // + tempName + "() entered ..." + "\n" ;
      return true ;
    } // if
    else  {     
      if (   this.mErrorNum == 0 && this.Rest_Of_Declarators( tempType, tempName ) == true ) {
        return true ;
      } // if       
    } // else 
    
    return false ;
  } // Function_definition_or_declarators()
  
  
  private boolean Function_definition_without_ID( String tempType, String tempName ) {
    if ( this.mTokenLexical.PeekToken().GetTable() == 1 && 
         this.mTokenLexical.PeekToken().GetEntry() == 6 ) {
      // it's '('
      this.mTokenLexical.GetToken() ; // skip '('
      if ( this.mTokenLexical.PeekToken().GetTable() == 2 && 
           this.mTokenLexical.PeekToken().GetEntry() == 6 ) {
        // it's void
        this.mTokenLexical.GetToken() ; // skip 'void' 
      } // if
      else if ( this.Formal_Parameter_List( ) == true ) {      
      } // else if
      else if ( this.mTokenLexical.PeekToken().GetTable() == 1 && 
                this.mTokenLexical.PeekToken().GetEntry() == 7 ) {
        // only ')'  
      } // else if
      else {
        this.WhichError( this.mTokenLexical.PeekToken(), 1958  ) ;  
        return false ;  
      } // else
    
      if ( this.mTokenLexical.PeekToken().GetTable() == 1 &&
           this.mTokenLexical.PeekToken().GetEntry() == 7 ) {
        // it's ')'
        this.mTokenLexical.GetToken() ; // skip ')'
        this.mTempFunction = new Function( tempType ) ; // Set Type
        if ( this.mCallTable5.size() != 0 ) {
          ArrayList<TokenInfo> tempTable5 = this.mTokenLexical.GetTable5() ;
          // tempTable5.addAll(  this.mCallTable5 ) ;
          for ( int i = 0 ; i < this.mCallTable5.size() ; i++ ) {
            tempTable5.add(  this.mCallTable5.get(  i ) ) ;
          } // for
          
          this.mTokenLexical.SetTable5(  tempTable5 ) ;
        } // if
 
        if ( this.Compound_Statement(  )  == true ) {
          // System.out.println( "Success Compound_Statement"  ) ;
          this.mTempFunction.SetFunctionName( tempName ) ; // Set Name
          if ( this.mCallTable5.size() != 0 ) {
            this.mTempFunction.SetCallTable5( this.mCallTable5 ) ; // Add CallTable5
            this.mTokenLexical.ModifyDomain( this.mDomain  ) ;
            this.mDomain-- ;
            // System.out.println(  " Domain is " + this.mDomain + " 
            // Table5 size is "  + this.mTokenLexical.GetTable5().size() );
          } // if
          // ****************************set lexical LineInfo****************************//
          Lexical tempLexical = this.mTempFunction.GetTokenLexical() ;
          tempLexical.SetLexicalFile( this.MakeFunctionLineInfo() ) ;
          this.mTempFunction.SetTokenLexical( tempLexical ) ;
          this.mTempFunction.SetCorrectStatement( true ) ;
          // ************************Check Definition****************************//
          this.mOutputValue = this.mOutputValue + this.mTokenLexical.MakeFunction(  this.mTempFunction ) ;
          // *******************************************************/
          return true ;    
        } // if
        else { // error Compound_Statement
          return false ;  
        } // else
      } // if
      else { // error ')'
        this.WhichError( this.mTokenLexical.PeekToken(), 1988 ) ;  
        return false ;  
      } // else
    } // if
    else { // error '('
      // this.WhichError( this.mTokenLexical.PeekToken(), 1993 ) ;
      return false ;  
    } // else
  } // Function_definition_without_ID()
  
  private TokenInfo Rest_Of_Declarators_GetId( String tempType, String tempName ) {
    TokenInfo tempTokenInfo = new TokenInfo() ;
    int iTempType = this.Return_Type_Specifier( tempType ) ; // Find the Type(String to Int)
    tempTokenInfo.SetTable( 5 ) ; // Set Table
    tempTokenInfo.SetType( iTempType  )  ; // Set Type
    tempTokenInfo.SetValue( tempName ) ; // Set Name
    if (  this.mTokenLexical.PeekToken().GetTable() == 1 &&
         this.mTokenLexical.PeekToken().GetEntry() == 15 ) { // It's '['
      this.mTokenLexical.GetToken() ; // SKip '['
      if ( this.mTokenLexical.PeekToken().GetTable() == 3 ||
           this.mTokenLexical.PeekToken().GetTable() == 4 ) { // It's Constant
        int tempNum = ( int ) this.mTokenLexical.PeekToken().GetValue_num() ;
        this.mTokenLexical.GetToken() ; // Skip Constant 
        if ( this.mTokenLexical.PeekToken().GetTable() == 1 &&
             this.mTokenLexical.PeekToken().GetEntry() == 16 ) { // It's ']'
          this.mTokenLexical.GetToken() ; // Skip ']'
          Table7 tempTable7 = new Table7( tempTokenInfo.GetType(), tempNum ) ; 
          tempTokenInfo.SetTable7( tempTable7 ) ;
          tempTokenInfo.SetType( 9 ) ; // Set it's Array         
        } // if
        else { // error ']'
          this.WhichError( this.mTokenLexical.PeekToken(), 2020 ) ;
          return null ;  
        } // else
      } // if
      else { // error Constant
        this.WhichError( this.mTokenLexical.PeekToken(), 2025 ) ;
        return null ;  
      } // else       
    } // if

    return tempTokenInfo ;
  } // Rest_Of_Declarators_GetId()
  
  private boolean Rest_Of_Declarators( String tempType, String tempName ) {
    TokenInfo tempTokenInfo = new TokenInfo() ;
    tempTokenInfo = this.Rest_Of_Declarators_GetId( tempType, tempName ) ;
    // Set the Id Info
    if ( tempTokenInfo != null  ) {
      // System.out.println( "Enter Rest_Of " + 
      // tempTokenInfo.GetValue() + " " + tempTokenInfo.GetType() );
      String sDefinition = this.mTokenLexical.MakeTable5( tempTokenInfo, this.mDomain ) ; 
      if ( this.mDomain == 1  )
        this.mOutputValue = this.mOutputValue + sDefinition + "\n" ;
      while ( this.mTokenLexical.PeekToken().GetTable() == 1 && 
              this.mTokenLexical.PeekToken().GetEntry() == 37 ) {
        // it's ',' Continue adding
        this.mTokenLexical.GetToken() ; // Skip ','
        if ( this.mTokenLexical.PeekToken().GetTable() == 5 ) {
          tempName = this.mTokenLexical.PeekToken().GetValue() ; // Store Id Name
          this.mTokenLexical.GetToken() ; // Skip Id
          tempTokenInfo = this.Rest_Of_Declarators_GetId( tempType, tempName ) ;
          if ( tempTokenInfo != null ) {
            sDefinition = this.mTokenLexical.MakeTable5( tempTokenInfo, this.mDomain ) ;
            if ( this.mDomain == 1  ) 
              this.mOutputValue = this.mOutputValue + sDefinition + "\n" ;
          } // if
          else { // error Id definition
            return false ;
          } // else 
        } // if
        else {
          this.WhichError( this.mTokenLexical.PeekToken(), 2056 ) ; // error Id
          return false ;
        } // else
      } // while
                
      if ( this.mTokenLexical.PeekToken().GetTable() == 1 &&
           this.mTokenLexical.PeekToken().GetEntry() == 5 ) { // it's ';'
        this.mTokenLexical.GetToken() ;
        return  true ;    
      } // if
      else { // error ';'
        this.WhichError( this.mTokenLexical.PeekToken(), 2068 ) ; 
        return false ;
      } // else 
    } // if
    
    return false ;
  } // Rest_Of_Declarators()
  
  private TokenInfo Formal_Parameter_List_GetID() {
    TokenInfo tempTokenInfo = new TokenInfo() ;
    if ( this.Type_Specifier() == true ) {
      tempTokenInfo.SetType( this.Return_Type_Specifier( this.mTokenLexical.PeekToken().GetValue() ) ) ;
      tempTokenInfo.SetTable( 5 ) ; // it's id
      this.mTokenLexical.GetToken() ; // skip Type_Specifier
      if ( this.mTokenLexical.PeekToken().GetTable() == 1 &&
           this.mTokenLexical.PeekToken().GetEntry() == 23 ) {
        // tempTokenInfo.SetCallByValue( true ) ;
        this.mTokenLexical.GetToken() ; // skip '&'
      } // if
    
      if ( this.mTokenLexical.PeekToken().GetTable() == 5  ) { // It's Id
        String tempString = this.mTokenLexical.PeekToken().GetValue() ; // Store Id String Value
        this.mTokenLexical.GetToken() ; // skip Id
        // *********************Array**************************//
        if ( this.mTokenLexical.PeekToken().GetTable() == 1 && 
             this.mTokenLexical.PeekToken().GetEntry() == 15 ) { // it's '['
          this.mTokenLexical.GetToken() ; // skip '['  
          if ( this.mTokenLexical.PeekToken().GetTable() == 3 ||
               this.mTokenLexical.PeekToken().GetTable() == 4 ) { // it's Constant
            int tempNum = ( int ) this.mTokenLexical.PeekToken().GetValue_num() ; 
            this.mTokenLexical.GetToken() ; // skip Constant
            if ( this.mTokenLexical.PeekToken().GetTable() == 1 &&
                 this.mTokenLexical.PeekToken().GetEntry() == 16 ) { // it's ']'
              this.mTokenLexical.GetToken() ; // skip ']' 
              Table7 tempTable7 = new Table7( tempTokenInfo.GetType(), tempNum ) ;
              tempTokenInfo.SetTable7( tempTable7 ) ;
              tempTokenInfo.SetType( 9 ) ; // Set it's Array
            } // if
            else {
              this.WhichError(  this.mTokenLexical.PeekToken(), 2107 ) ; // error ']'         
              return null ;              
            } // else
          } // if
          else {
            this.WhichError(  this.mTokenLexical.PeekToken(), 2112 ) ;  // error Constant        
            return null ;
          } // else
        } // if
       
        tempTokenInfo.SetValue( tempString ) ; // Set the Value    
        return tempTokenInfo ;
      } // if
      else {
        this.WhichError(  this.mTokenLexical.PeekToken(), 2122 ) ; // error ID
        return null ;
      } // else
    } // if
  
    return null ;
  } // Formal_Parameter_List_GetID()
  
  private boolean Formal_Parameter_List() {
    ArrayList<TokenInfo> aTempTokenArray = new ArrayList<TokenInfo>() ;
    TokenInfo tempTokenInfo = new TokenInfo() ;
    tempTokenInfo = this.Formal_Parameter_List_GetID() ; // Get the Call Value ID or Array
    if ( tempTokenInfo != null ) {
      this.mDomain++ ;
      tempTokenInfo.SetDomain( this.mDomain  ) ;
      aTempTokenArray.add( tempTokenInfo ) ; // add to tokenArray
      while ( this.mTokenLexical.PeekToken().GetTable() == 1 &&
              this.mTokenLexical.PeekToken().GetEntry() == 37 ) {
        this.mTokenLexical.GetToken() ; // skip ',' 
        tempTokenInfo = this.Formal_Parameter_List_GetID() ;
        if ( tempTokenInfo != null  ) {
          tempTokenInfo.SetDomain( this.mDomain  ) ;
          aTempTokenArray.add( tempTokenInfo ) ; // add to tokenArray  
        } // if
        else {
          return false ;  
        } // else
      } // while
      
      this.mCallTable5 = aTempTokenArray ;
      
      return true ;
    } // if
    
    return false ;
  } // Formal_Parameter_List()
  
  
  private boolean Rest_Of_Identifier_Started_Basic_Exp( TokenInfo correctId,
                                                        int errorIdLine, String errorId, String FuncName ) {
    TokenInfo tempPPMM = new TokenInfo() ;
    if ( mErrorNum == 0 && this.mTokenLexical.PeekToken().GetTable() == 1 && 
         this.mTokenLexical.PeekToken().GetEntry() == 15 ) {
      // it's '['
      if ( this.mErrorNum ==  0 && correctId == null ) {
        System.out.print( "> Line " + errorIdLine + " : " ) ;
        System.out.println( this.mErrorType[2] + "'" + errorId + "'" ) ;
        this.mErrorNum++ ;
        // System.out.println( "ErrorLine-->" + 2227 ) ;
        return false ;
      } // if
      
      this.mTokenLexical.GetToken() ; // Skip '['
      if ( this.Expression( ) == true ) { // it's Exp
        if ( this.mTokenLexical.PeekToken().GetTable() == 1 &&
             this.mTokenLexical.PeekToken().GetEntry() == 16 ) {
          // it's ']'
          this.mTokenLexical.GetToken() ; // Skip ']'
          if ( this.Assignment_Operator( correctId, errorIdLine, errorId ) == true ) {
            TokenInfo tempTokenInfo = this.mTokenLexical.GetToken() ; 
            if ( this.Basic_Expression( ) == true ) {
              // It's Assignment Operator
              return true ;
            } // if
            else { // error Assignment Operator
              return false ;
            } // else
          } // if
          else { // it's PPMM  ????? 
            if ( this.mTokenLexical.PeekToken().GetTable() == 1  &&
                 ( this.mTokenLexical.PeekToken().GetEntry() == 33 ||
                   this.mTokenLexical.PeekToken().GetEntry() == 34 ) ) {
            // It's PP or MM
              tempPPMM = this.mTokenLexical.GetToken() ; // Skip PPMM
            } // if
            
            if ( this.Romce_And_Romloe() == true ) {
              return true ;
            } // if
            else { // error Romce_And_Romloe
              return false ;
            } // else
          } // else
        } // if
        else { // error ']'
          this.WhichError( this.mTokenLexical.PeekToken(), 2197 ) ;
          return false ;
        } // else
      } // if
      else { // error Exp
        return false ;
      } // else     
    } // if
    else if ( mErrorNum == 0 && this.Assignment_Operator( correctId, errorIdLine, errorId )  == true ) { 
      TokenInfo tempTokenInfo = this.mTokenLexical.GetToken() ;  
      // System.out.println( "Enter Assignment_Operator " 
      // + this.mTokenLexical.PeekToken().GetValue() ) ;
      if ( this.Basic_Expression( ) == true ) {          
        return true ;
      } // if
      else {
        return false ;
      } // else
    } // else if
    else if ( mErrorNum == 0 && this.mTokenLexical.PeekToken().GetTable() == 1  && 
              ( this.mTokenLexical.PeekToken().GetEntry() == 33 || 
                this.mTokenLexical.PeekToken().GetEntry() == 34   ) ) {
      // It's PPMM
      if (  this.mErrorNum ==  0 && correctId == null ) {
        System.out.print( "> Line " + errorIdLine + " : " ) ;
        System.out.println( this.mErrorType[2] + "'" + errorId + "'" ) ;
        this.mErrorNum++ ;
        // System.out.println( "ErrorLine-->" + 2290 ) ;
        return false ;
      } // if
      
      tempPPMM = this.mTokenLexical.GetToken() ; // Skip PPMM
      if ( this.Romce_And_Romloe() == true ) {
        return true ;
      } // if
      else {
        return false ;
      } // else     
    } // else if
    else if ( mErrorNum == 0 && correctId != null && this.Romce_And_Romloe() == true ) {
      return true ;  
    } // else if
    else if ( mErrorNum == 0 && this.mTokenLexical.PeekToken().GetTable() == 1 && 
              this.mTokenLexical.PeekToken().GetEntry() == 6 ) { // it's '('   
      if ( this.mErrorNum ==  0 &&  this.CheckFuncId( FuncName ) == false ) {
        System.out.print( "> Line " + errorIdLine + " : " ) ;
        System.out.println( this.mErrorType[2] + "'" + errorId + "'" ) ;
        this.mErrorNum++ ;
        // System.out.println( "ErrorLine-->" + 2312 ) ;
        return false ;
      } // if
      
      this.mTokenLexical.GetToken() ; // skip '('
      if ( this.mTokenLexical.PeekToken().GetTable() == 1 &&
           this.mTokenLexical.PeekToken().GetEntry() == 7 ) { // it's ')'
        // Don't Have Actual_Parameter_List
        this.mTokenLexical.GetToken() ; // skip ')'
        if ( this.Romce_And_Romloe() == true ) {
          return true ;
        } // if
        else {
          return false ;
        } // else
      } // if
      else { // It's Actual_Parameter_List
        if ( this.Actual_Parameter_List( ) == true ) { // Check the Parameter List
          if ( this.mTokenLexical.PeekToken().GetTable() == 1 && 
               this.mTokenLexical.PeekToken().GetEntry() == 7 ) { // it's ')'
            this.mTokenLexical.GetToken() ;
            if ( this.Romce_And_Romloe() == true ) {
              return true ;
            } // if
            else { // error Romce_And_Romloe
              return false ;
            } // else
          } // if
          else { // error ')'
            this.WhichError( this.mTokenLexical.PeekToken(), 2274 ) ;
            return false ;
          } // else
        } // if
        else { // error Actual_Parameter_List()
          return false ;
        } // else
      } // else      
    } // else if
    
    
    if ( this.mErrorNum == 0  ) {
      this.WhichError(  this.mTokenLexical.PeekToken(), 3250 ) ;  
    } // if
    else if ( this.mErrorNum == 1 && errorIdLine != 0   ) {
      System.out.print( "> Line " + errorIdLine + " : " ) ;
      System.out.println( this.mErrorType[2] + "'" + errorId + "'" ) ; 
      // System.out.println( "2456" ) ;
    } // else if
    
    return false ;
  } // Rest_Of_Identifier_Started_Basic_Exp()


  
  private boolean Rest_Of_PPMM_Identifier_Started_Basic_Exp( String errorId,
                                                             TokenInfo correctId, int errorIdLine ) {
    if ( this.mErrorNum == 0 && this.mTokenLexical.PeekToken().GetTable() == 1 && 
         this.mTokenLexical.PeekToken().GetEntry() == 15 ) {
      this.mTokenLexical.GetToken() ; // Skip '['
      if ( errorIdLine != 0 ) {
        System.out.print( "> Line " + errorIdLine + " : " ) ;
        System.out.println( this.mErrorType[2] + "'" + errorId + "'" ) ; 
        // System.out.println( "2496" ) ;
      } // if
      
      if ( this.Expression(  ) == true ) {
        if ( this.mTokenLexical.PeekToken().GetTable() == 1 && 
             this.mTokenLexical.PeekToken().GetEntry() == 16 ) {
          this.mTokenLexical.GetToken() ; // Skip ']'
          if ( this.Romce_And_Romloe() == true ) {
            return true ;
          } // if
          else { // error Romce_And_Romloe
            return false ;
          } // else
        } // if
        else { // error ']'
          this.WhichError( this.mTokenLexical.PeekToken(), 2311  ) ;
          return false ;
        } // else
      } // if
      else { // error Expression
        return false ;
      } // else
    } // if
    else { // Only have Romce_And_Romloe
      if ( this.mErrorNum == 0 && this.Romce_And_Romloe( ) == true ) {
        return true ;
      } // if
      else { // error Romce_And_Romle
        if ( this.mErrorNum == 1 && errorIdLine != 0 ) {
          System.out.print( "> Line " + errorIdLine + " : " ) ;
          System.out.println( this.mErrorType[2] + "'" + errorId + "'" ) ; 
          // System.out.println( "2496" ) ;
        } // if
        else if ( this.mErrorNum == 0 ) {
          this.WhichError(  this.mTokenLexical.PeekToken(), 3250 ) ;
        } // else if
        
        return false ;
      } // else
    } // else    
  } // Rest_Of_PPMM_Identifier_Started_Basic_Exp()
  
  
  
  private boolean Assignment_Operator( TokenInfo correctId,
                                       int errorIdLine, String errorId ) {
    if ( this.mTokenLexical.PeekToken().GetTable() == 1  && 
         ( this.mTokenLexical.PeekToken().GetEntry() == 8  || 
           this.mTokenLexical.PeekToken().GetEntry() == 28 || 
           this.mTokenLexical.PeekToken().GetEntry() == 29 || 
           this.mTokenLexical.PeekToken().GetEntry() == 30 || 
           this.mTokenLexical.PeekToken().GetEntry() == 31 || 
           this.mTokenLexical.PeekToken().GetEntry() == 32  ) ) {
      // ****************Check the Id is Define or not****************//
      if (  this.mErrorNum ==  0 && correctId ==  null ) {
        System.out.print( "> Line " + errorIdLine + " : " ) ;
        System.out.println( this.mErrorType[2] + "'" + errorId + "'" ) ;
        this.mErrorNum++ ;
        // System.out.println( "ErrorLine-->" + 2421 ) ;
        return false ;
      } // if
      else {  
        return true ;
      } // else
      // ****************************not return false*****************//
    } // if
    else {
      return false ;
    } // else
  } // Assignment_Operator()
    
  private boolean Actual_Parameter_List( ) {
    // System.out.println( "Enter Actual_Parameter_List( ) "
    // + this.mTokenLexical.PeekToken().GetValue() ) ;
    if (  this.Basic_Expression() == true ) {
      while ( this.mTokenLexical.PeekToken().GetTable() == 1 && 
              this.mTokenLexical.PeekToken().GetEntry() == 37 ) {
        // it's ','
        this.mTokenLexical.GetToken() ; // skip ','
        if ( this.Basic_Expression( ) == true ) {        
        } // if
        else { // error Basic_Exp
          return false ;
        } // else
      } // while
      
      // System.out.println( "Success Actual Basic_Expression "
      // + this.mTokenLexical.PeekToken().GetValue() ) ;
      return true ;
    } // if
    
    return false ;
  } // Actual_Parameter_List()
  
  private boolean Romce_And_Romloe( ) {
    if ( this.Rest_Of_Maybe_Logical_Or_Exp() == true ) {
      // System.out.println( "Rest_Of_Maybe_Logical_Or_Exp() "
      // + this.mTokenLexical.PeekToken().GetValue() ) ;
      if ( this.mTokenLexical.PeekToken().GetTable() == 1 && 
           this.mTokenLexical.PeekToken().GetEntry() == 38 ) {
        // it's '?'
        this.mTokenLexical.GetToken() ; // Skip '?'
        if ( this.Basic_Expression() == true ) {
          if (  this.mTokenLexical.PeekToken().GetTable() == 1 &&
               this.mTokenLexical.PeekToken().GetEntry() == 39 ) {
            // it's ':'
            this.mTokenLexical.GetToken() ; // Skip ':'
            if ( this.Basic_Expression( ) == true ) {
              return true ;
            } // if
            else { // error Basic
              return false ;
            } // else
          } // if
          else { // error ':'
            this.WhichError( this.mTokenLexical.PeekToken(), 2398 ) ;
            return false ;
          } // else
        } // if
        else { // error Basic_Exp
          return false ;
        } // else
      } // if
      
      return true ; // no ? Compare
    } // if
    else { // error Rest_Of_Maybe_Logical_Or_Exp()
      return false ;
    } // else
  } // Romce_And_Romloe()
  
  private boolean Rest_Of_Maybe_Logical_Or_Exp() {
    if ( this.Rest_Of_Maybe_Logical_And_Exp() == true ) {
      // System.out.println( "Enter Rest_Of_Maybe_Logical_And_Exp() 
      // " + this.mTokenLexical.PeekToken().GetValue() ) ;
      while ( this.mTokenLexical.PeekToken().GetTable() == 1 && 
              this.mTokenLexical.PeekToken().GetEntry() == 27 ) {
        // it's OR
        this.mTokenLexical.GetToken() ; // Skip OR
        if ( this.Maybe_Logical_And_Exp() == true ) {
        } // if
        else { // it's error Maybe_Logical_And_Exp()
          return false ;
        } // else
      } // while
      
      return true ; // only Rest_Of_Maybe_Logical_And_Exp()
    } // if
    else { // error Rest_Of_Maybe_Logical_And_Exp()
      return false ;
    } // else 
  } // Rest_Of_Maybe_Logical_Or_Exp()
  
  private boolean Maybe_Logical_And_Exp() {
    if ( this.Maybe_Bit_Or_Exp() == true ) {
      // System.out.println( "Enter Maybe_Bit_Or_Exp() " + 
      // this.mTokenLexical.PeekToken().GetValue() ) ;
      while ( this.mTokenLexical.PeekToken().GetTable() == 1 && 
              this.mTokenLexical.PeekToken().GetEntry() == 26 ) {
        // it's AND
        this.mTokenLexical.GetToken() ; // Skip AND
        if ( this.Maybe_Bit_Or_Exp() == true  ) {       
        } // if
        else { // error 
          return false ;
        } // else 
      } // while 
      
      return true ; // only one Maybe_Bit_Or_exp()
    } // if
    else { // error Maybe_Bit_Or_Exp()
      return false ;
    } // else 
  } // Maybe_Logical_And_Exp()
  
  private boolean Rest_Of_Maybe_Logical_And_Exp() {
    if ( this.Rest_Of_Maybe_Bit_Or_Exp() == true ) {
      // System.out.println( "Rest_Of_Maybe_Bit_Or_Exp() " 
      // + this.mTokenLexical.PeekToken().GetValue() ) ;
      while ( this.mTokenLexical.PeekToken().GetTable() == 1 && 
              this.mTokenLexical.PeekToken().GetEntry() == 26 ) {
        // it's And
        this.mTokenLexical.GetToken() ; // Skip AND
        if ( this.Maybe_Bit_Or_Exp() ) {    
        } // if
        else { // error Maybe_Bit_Or_Exp
          return false ;
        } // else
      } // while
      
      return true ; // only Rest_Of_Maybe_Bit_Or_Exp()
    } // if
    else { // error Rest_Of_Maybe_Bit_Or_Exp()
      return false ;
    } // else  
  } // Rest_Of_Maybe_Logical_And_Exp()
  
  private boolean Maybe_Bit_Or_Exp() {
    if ( this.Maybe_Bit_Ex_Or_Exp() == true ) {
      // System.out.println( "Maybe_Bit_Ex_Or_Exp() " + 
      // this.mTokenLexical.PeekToken().GetValue() ) ;
      while ( this.mTokenLexical.PeekToken().GetEntry() == 1 && 
              this.mTokenLexical.PeekToken().GetTable() == 24  ) {
        // it's '|'
        this.mTokenLexical.GetToken() ; // SKip '|'
        if ( this.Maybe_Bit_Ex_Or_Exp() == true ) {
        } // if
        else { // Maybe_Bit_Ex_Or_Exp()
          return false ;
        } // else
      } // while
    
      return true ; // only Maybe_Bit_Ex_Or_Exp()
    } // if 
    else { // error Maybe_Bit_Ex_Or_Exp()
      return false ;
    } // else
  } // Maybe_Bit_Or_Exp()
  
  private boolean Rest_Of_Maybe_Bit_Or_Exp() {
    if ( this.Rest_Of_Maybe_Bit_Ex_Or_Exp() == true ) {
      // System.out.println( "Rest_Of_Maybe_Bit_Or_Exp() "
      // + this.mTokenLexical.PeekToken().GetValue() ) ;
      while ( this.mTokenLexical.PeekToken().GetTable() == 1 && 
              this.mTokenLexical.PeekToken().GetEntry() == 24 ) {
        // it's '|'
        this.mTokenLexical.GetToken() ; // Skip '|'
        if ( this.Maybe_Bit_Ex_Or_Exp() == true ) {
        } // if
        else { // error Maybe_Bit_Ex_Or_Exp()
          return false ;
        } // else
      } // while
      
      return true ; // only Rest_Of_Maybe_Bit_Ex_Or_Exp()
    } // if
    else {
      return false ;
    } // else
  } // Rest_Of_Maybe_Bit_Or_Exp()
  
  private boolean Maybe_Bit_Ex_Or_Exp() {
    if ( this.Maybe_Bit_And_Exp() == true ) {
      // System.out.println( "Maybe_Bit_And_Exp() " + 
      // this.mTokenLexical.PeekToken().GetValue() ) ;
      while ( this.mTokenLexical.PeekToken().GetTable() == 1 &&
              this.mTokenLexical.PeekToken().GetEntry() == 20 ) {
        // it's '^'  be careful of BUGGGG
        this.mTokenLexical.GetToken() ; // Skip '^'
        
        if ( this.Maybe_Bit_And_Exp()  == true ) {
        } // if
        else { // error Maybe_Bit_And_Exp()
          return false ;
        } // else
      } // while
      
      return true ; // only one Maybe_Bit_Ex_Or_Exp()
    } // if
    else { // error Maybe_Bit_And_Exp()
      return false ;
    } // else
  } // Maybe_Bit_Ex_Or_Exp()
  
  private boolean Rest_Of_Maybe_Bit_Ex_Or_Exp() {
    if ( this.Rest_Of_Maybe_Bit_And_Exp() == true ) {
      // System.out.println( "Rest_Of_Maybe_Bit_And_Exp()" 
      // + this.mTokenLexical.PeekToken().GetValue() ) ;
      while ( this.mTokenLexical.PeekToken().GetTable() == 1 && 
              this.mTokenLexical.PeekToken().GetEntry() == 20 ) {
        // it's '^' be careful of BuG
        this.mTokenLexical.GetToken() ; // Skip '^'
        if ( this.Maybe_Bit_And_Exp()  == true ) {
        } // if
        else { // error Maybe_Bit_And_Exp()
          return false ;
        } // else
      } // while
      
      return true ; // only Rest_Of_Maybe_Bit_And_Exp()
    } // if
    else { // error Rest_Of_Maybe_Bit_And_Exp()
      return false ;
    } // else
  } // Rest_Of_Maybe_Bit_Ex_Or_Exp()
  
  private boolean Maybe_Bit_And_Exp() {
    if ( this.Maybe_Equality_Exp() == true ) {
    // System.out.println( "Maybe_Equality_Exp()" +
    // this.mTokenLexical.PeekToken().GetValue() ) ;
      while ( this.mTokenLexical.PeekToken().GetTable() == 1 && 
              this.mTokenLexical.PeekToken().GetEntry() == 23 ) {
        // it's '&'
        this.mTokenLexical.GetToken() ; // Skip '&'
        if ( this.Maybe_Equality_Exp() == true ) {
        } // if
        else { // error Maybe_Equality_Exp()
          return false ;
        } // else
      } // while
    
      return true ; // only one Maybe_Bit_And_Exp()
    } // if
    else { // error Maybe_Equality_Exp()
      return false ;
    } // else
  } // Maybe_Bit_And_Exp()

  private boolean Rest_Of_Maybe_Bit_And_Exp() {
    if ( this.Rest_Of_Maybe_Equality_Exp() == true ) {
      // System.out.println( "Rest_Of_Maybe_Equality_Exp() "
      // + this.mTokenLexical.PeekToken().GetValue() ) ;
      while ( this.mTokenLexical.PeekToken().GetTable() == 1 && 
              this.mTokenLexical.PeekToken().GetEntry() == 23 ) {
        // it's '&'
        this.mTokenLexical.GetToken() ; // skip '&'
        if ( this.Maybe_Equality_Exp() == true ) {
        } // if
        else {
          return false ;
        } // else 
      } // while
      
      return true ; // only Rest_Of_Maybe_Equality_Exp()
    } // if
    else { // error Rest_Of_Maybe_Equality_Exp()
      return false ;
    } // else
  } // Rest_Of_Maybe_Bit_And_Exp()
  
  private boolean Maybe_Equality_Exp() {
    if ( this.Maybe_Relational_Exp() == true  ) {
      // System.out.println( "Maybe_Relational_Exp() " + 
      // this.mTokenLexical.PeekToken().GetValue() ) ;
      while ( this.mTokenLexical.PeekToken().GetTable() == 1  && 
              ( this.mTokenLexical.PeekToken().GetEntry() == 21 || 
                this.mTokenLexical.PeekToken().GetEntry() == 22 ) ) {
        // It's EQ or NEQ
        TokenInfo tempEQ_NEQ = this.mTokenLexical.GetToken() ; // skip EQ or NEQ 
        if ( this.Maybe_Relational_Exp() == true ) {
        } // if
        else { // error Maybe_Relational_Exp()
          return false ;
        } // else
      } // while
      
      return true ;
    } // if
    else { // error May_Relational_Exp()
      return false ;
    } // else
  } // Maybe_Equality_Exp()
  
  private boolean Rest_Of_Maybe_Equality_Exp() {
    if ( this.Rest_Of_Maybe_Relational_Exp() == true ) {
    // System.out.println( "Rest_Of_Maybe_Relational_Exp()"
    // + this.mTokenLexical.PeekToken().GetValue() ) ;
      while ( this.mTokenLexical.PeekToken().GetTable() == 1 && 
              ( this.mTokenLexical.PeekToken().GetEntry() == 21 || 
                this.mTokenLexical.PeekToken().GetEntry() == 22 ) ) {
        // it's EQ or NEQ
        TokenInfo tempEQ_NEQ = this.mTokenLexical.GetToken() ; // Skip EQ or NEQ 
        if ( this.Maybe_Relational_Exp() == true ) {
        } // if
        else { // error Maybe_Relational_Exp()
          return false ;
        } // else
      } // while
    
      return true ;
    } // if
    else { // error Rest_Of_Maybe_Relational_Exp()
      return false ;
    } // else
  } // Rest_Of_Maybe_Equality_Exp()
  
  private boolean Maybe_Relational_Exp() {
    if ( this.Maybe_Shift_Exp() == true ) {
      // System.out.println( "Maybe_Shift_Exp() " + 
      // this.mTokenLexical.PeekToken().GetValue() ) ;
      while ( this.mTokenLexical.PeekToken().GetTable() == 1 && 
              ( this.mTokenLexical.PeekToken().GetEntry() == 14 || 
                this.mTokenLexical.PeekToken().GetEntry() == 13 || 
                this.mTokenLexical.PeekToken().GetEntry() == 3  || 
                this.mTokenLexical.PeekToken().GetEntry() == 4  ) ) {
        // it's boolean operator 
        TokenInfo tempRelateOperator = this.mTokenLexical.GetToken() ;
        // skip Relational Operator
        if ( this.Maybe_Shift_Exp() == true ) {
        } // if
        else { // error Maybe_Shift_Exp()
          return false ;
        } // else 
      } // while
      
      return true ;
    } // if
    else { // error Maybe_Shift_Exp()
      return false ;
    } // else
  } // Maybe_Relational_Exp() 
  
  private boolean Rest_Of_Maybe_Relational_Exp() {
    if ( this.Rest_Of_Maybe_Shift_Exp() == true ) {
      // System.out.println( "Rest_Of_Maybe_Shift_Exp() 
      // " + this.mTokenLexical.PeekToken().GetValue() ) ;
      while ( this.mTokenLexical.PeekToken().GetTable() == 1  && 
              ( this.mTokenLexical.PeekToken().GetEntry() == 14 || 
                this.mTokenLexical.PeekToken().GetEntry() == 13 || 
                this.mTokenLexical.PeekToken().GetEntry() == 3  || 
                this.mTokenLexical.PeekToken().GetEntry() == 4    ) ) {
        // it's boolean operator 
        TokenInfo tempRelateOperator = this.mTokenLexical.GetToken() ;
        // skip Relational Operator
        if ( this.Maybe_Shift_Exp() == true ) {
        } // if
        else { // error Maybe_Shift_Exp()
          return false ;
        } // else 
      } // while
          
      return true ;
    } // if
    else { // error Rest_Of_Maybe_Shift_Exp()
      return false ;
    } // else
  } // Rest_Of_Maybe_Relational_Exp()
  
  private boolean Maybe_Shift_Exp() {
    if ( this.Maybe_Additive_Exp() == true ) {
      // System.out.println( "Maybe_Additive_Exp()  " 
      // + this.mTokenLexical.PeekToken().GetValue() ) ;
      while ( this.mTokenLexical.PeekToken().GetTable() == 1 && 
              ( this.mTokenLexical.PeekToken().GetEntry() == 36 ||
                this.mTokenLexical.PeekToken().GetEntry() == 35 ) ) {
        // it's LS or RS
        TokenInfo tempLS_RS = this.mTokenLexical.GetToken() ; // Skip LS or RS
        if ( this.Maybe_Additive_Exp() == true ) {
        } // if
        else { // error Maybe_Additive_Exp()
          return false ;
        } // else
      } // while
      
      return true ;
    } // if
    else { // error Maybe_Additive_Exp()
      return false ;
    } // else
  } // Maybe_Shift_Exp()
  
  private boolean Rest_Of_Maybe_Shift_Exp() {
    if ( this.Rest_Of_Maybe_Additive_Exp() == true ) {
      //  System.out.println( "Rest_Of_Maybe_Additive_Exp() 
      // " + this.mTokenLexical.PeekToken().GetValue() ) ;
      while ( this.mTokenLexical.PeekToken().GetTable() == 1  && 
              ( this.mTokenLexical.PeekToken().GetEntry() == 36 ||
                this.mTokenLexical.PeekToken().GetEntry() == 35   ) ) {
      // it's LS or RS
        TokenInfo tempLS_RS = this.mTokenLexical.GetToken() ; // Skip LS or RS
        if ( this.Maybe_Additive_Exp() == true ) {
        } // if
        else { // error Maybe_Additive_Exp()
          return false ;
        } // else
      } // while
          
      return true ;
    } // if
    else { // error Rest_Of_Maybe_Additive_Exp()
      return false ;
    } // else  
  } // Rest_Of_Maybe_Shift_Exp()
  
  private boolean Maybe_Additive_Exp() {
    if ( this.Maybe_Mult_Exp() == true ) {
      // System.out.println( "Maybe_Mult_Exp() " + 
      // this.mTokenLexical.PeekToken().GetValue() ) ;
      while ( this.mTokenLexical.PeekToken().GetTable() == 1 &&
              ( this.mTokenLexical.PeekToken().GetEntry() == 9 ||
                this.mTokenLexical.PeekToken().GetEntry() == 10  ) ) {
        TokenInfo tempPlus_Miner = this.mTokenLexical.GetToken() ;
        // skip Plus or Miner
        if ( this.Maybe_Mult_Exp() == true ) {
        } // if
        else { // error Maybe_Mul_Exp()
          return false ;
        } // else
      } // while
      
      return true ;
    } // if
    else { // error Maybe_Mul_Exp()
      return false ;
    } // else
  } // Maybe_Additive_Exp()
  
  private boolean Rest_Of_Maybe_Additive_Exp() {
    if ( this.Rest_Of_Maybe_Mult_Exp() == true ) { 
      // System.out.println( "Rest_Of_Maybe_Mult_Exp()" +
      // this.mTokenLexical.PeekToken().GetValue() ) ;
      while ( this.mTokenLexical.PeekToken().GetTable() == 1 &&
              ( this.mTokenLexical.PeekToken().GetEntry() == 9 ||
                this.mTokenLexical.PeekToken().GetEntry() == 10  ) ) {
        TokenInfo tempPlus_Miner = this.mTokenLexical.GetToken() ;
        // skip Plus or Miner
        if ( this.Maybe_Mult_Exp() == true ) {
        } // if
        else { // error Maybe_Mul_Exp()
          return false ;
        } // else
      } // while
            
      return true ;
    } // if
    else { // error Rest_Of_Maybe_Mul_Exp()
      return false ;
    } // else
  } // Rest_Of_Maybe_Additive_Exp()
  
  private boolean Maybe_Mult_Exp() {
    // System.out.println( "Maybe_Mult_Exp() 1" + this.mTokenLexical.PeekToken().GetValue() ) ;
    if ( this.Unary_Exp() == true ) {
      // System.out.println( "Success Maybe_Mult_Exp() " 
      // + this.mTokenLexical.PeekToken().GetValue() ) ;
      if ( this.Rest_Of_Maybe_Mult_Exp() == true ) {
        return true ;
      } // if
      else { // error Rest_Of_Maybe_Mul_Exp()
        return false ;
      } // else
    } // if
    else { // error Unary_Exp()
      // System.out.println( "Fail Maybe_Mult_Exp() " 
      // + this.mTokenLexical.PeekToken().GetValue() ) ;
      return false ; 
    } // else 
  } // Maybe_Mult_Exp() ;
  
  private boolean Rest_Of_Maybe_Mult_Exp() {
    if ( this.mTokenLexical.PeekToken().GetTable() == 1  &&
         ( this.mTokenLexical.PeekToken().GetEntry() == 11 ||  
           this.mTokenLexical.PeekToken().GetEntry() == 12 || 
           this.mTokenLexical.PeekToken().GetEntry() == 19   ) ) {
      while ( this.mTokenLexical.PeekToken().GetTable() == 1  && 
              ( this.mTokenLexical.PeekToken().GetEntry() == 11 ||
                this.mTokenLexical.PeekToken().GetEntry() == 12 || 
                this.mTokenLexical.PeekToken().GetEntry() == 19  ) ) {
        // it's '*' or '/' or '%'
        TokenInfo tempMulOperator = this.mTokenLexical.GetToken() ;
        // Skip '*' or '/' or '%'
        if ( this.Unary_Exp() == true ) {
        } // if
        else {
          return false ;
        } // else
      } // while  
    } // if
  
    return true ;
  } // Rest_Of_Maybe_Mult_Exp()
  
  private boolean Unary_Exp() {
    if ( this.mTokenLexical.PeekToken().GetTable() == 1  &&
         ( this.mTokenLexical.PeekToken().GetEntry() == 9  ||
           this.mTokenLexical.PeekToken().GetEntry() == 10 ||
           this.mTokenLexical.PeekToken().GetEntry() == 25   ) ) {
      // it's Sign
      TokenInfo tempSign = this.mTokenLexical.GetToken() ; // Skip Sign
      while ( this.mTokenLexical.PeekToken().GetTable() == 1  && 
              ( this.mTokenLexical.PeekToken().GetEntry() == 9  || 
                this.mTokenLexical.PeekToken().GetEntry() == 10 ||
                this.mTokenLexical.PeekToken().GetEntry() == 25 ) ) {
        tempSign = this.mTokenLexical.GetToken() ; // Skip Sign 
      } // while
      
      // System.out.println( "After Sign " + this.mTokenLexical.PeekToken().GetValue() ) ;
      if ( this.Signed_Unary_Exp( false ) == true ) {
        return true ;
      } // if
      else { // error Signed_unary_Exp
        return false ;
      } // else
    } // if
    else if (  this.mTokenLexical.PeekToken().GetTable() == 1  && 
              (  this.mTokenLexical.PeekToken().GetEntry() == 33 || 
                this.mTokenLexical.PeekToken().GetEntry() == 34   ) ) {
      // it's PPMM
      TokenInfo tempPPMM = this.mTokenLexical.GetToken() ; // it's PP or MM
      if ( this.mTokenLexical.PeekToken().GetTable() == 5 ) { // it's Id
        // this.SetPastColRow() ; // Set the past Column and Row
        int errorIdLine = this.mTokenLexical.PeekToken().GetInputColumn() ;
        TokenInfo  tempId = this.CheckId(  this.mTokenLexical.PeekToken() ) ; 
        String errorId = this.mTokenLexical.PeekToken().GetValue() ;
        this.mTokenLexical.GetToken() ; // Skip Id
        if ( this.mTokenLexical.PeekToken().GetEntry() == 1 && 
             this.mTokenLexical.PeekToken().GetTable() == 15 ) { // it's '['
          this.mTokenLexical.GetToken() ; // Skip '['
          if ( tempId != null ) {
            if ( this.Expression() == true ) {
              if ( this.mTokenLexical.PeekToken().GetTable() == 1 && 
                   this.mTokenLexical.PeekToken().GetEntry() == 16 ) {
                // it's ']'
                this.mTokenLexical.GetToken() ; // Skip ']'
                return true ;
              } // if
              else { // error ']' 
                this.WhichError( this.mTokenLexical.PeekToken(), 2894 ) ;
                return false ;
              } // else
            } // if
            else { // error Expression
              return false ;
            } // else    
          } // if
          else {
            if ( this.mErrorNum ==  0  ) { 
              System.out.print( "> Line " + errorIdLine + " : " ) ;
              System.out.println(  this.mErrorType[2] + "'" + errorId  + "'" ) ;
              this.mErrorNum++ ;
              // System.out.println( "ErrorLine-->" + 3006 ) ;
            } // if
            
            return false ;
          } // else          
        } // if
        else { // no "[EXP]"
          if ( tempId != null ) {
            return true ;  
          } // if
          else {
            if ( this.mErrorNum ==  0  ) {
              System.out.print( "> Line " + errorIdLine + " : " ) ;
              System.out.println(  this.mErrorType[2] + "'" + errorId  + "'" ) ;
              this.mErrorNum++ ;
              // System.out.println( "ErrorLine-->" + 3018 ) ;
            } // if
            
            return false ;  
          } // else 
        } // else
      } // if
      else { // error ID
        this.WhichError( this.mTokenLexical.PeekToken(), 2922  ) ;
        return false ;
      } // else
    } // else if 
    else {
      if ( this.Signed_Unary_Exp( true ) == true ) {
        // System.out.println( "After UNSigned_Unary_Exp " + this.mTokenLexical.PeekToken().GetValue() ) ;
        return true ;
      } // if
    } // else

    if (   this.mErrorNum == 0 && this.mTokenLexical.PeekToken().GetValue().equals( ";" ) == true ) {
      return true ; 
    } // if
    else {
      if ( this.mErrorNum == 0  ) 
        this.WhichError(  this.mTokenLexical.PeekToken(), 3106 ) ;
      
      return false ;
    } // else
  } // Unary_Exp()
  
  private boolean Signed_Unary_Exp( boolean isUnsigned ) {
    int errorIdLine = 0 ;
    TokenInfo tempId = new TokenInfo() ;
    if ( this.mTokenLexical.PeekToken().GetTable() == 5 ) {
      // this.SetPastColRow() ; 
      // System.out.println( "Enter Signed_Unary_Exp Id " + 
      // isUnsigned + " " + this.mTokenLexical.PeekToken().GetValue() ) ;
      tempId = this.mTokenLexical.GetToken() ; // Skip Id

      
      if ( this.CheckFuncId( tempId.GetValue() ) == false && this.CheckId( tempId ) == null ) {
        // System.out.print( "> Line " + errorIdLine + " : " ) ;
        // System.out.println( this.mErrorType[2] + "'" + tempId.GetValue() + "'" ) ;
        // this.mErrorNum++ ;
        errorIdLine = tempId.GetInputColumn() ;
        // return false ;   
      } // if
      
      if ( this.mErrorNum == 0 &&  this.mTokenLexical.PeekToken().GetTable() == 1 && 
           this.mTokenLexical.PeekToken().GetEntry() == 6  ) { // it's '('
        if ( this.mErrorNum ==  0 && this.CheckFuncId( tempId.GetValue() ) == false  ) {
          System.out.print( "> Line " + errorIdLine + " : " ) ;
          System.out.println( this.mErrorType[2] + "'" + tempId.GetValue() + "'" ) ;
          this.mErrorNum++ ;
          // System.out.println( "ErrorLine-->" + 3054 ) ;
          return false ;
        } // if
        
        this.mTokenLexical.GetToken() ; // Skip '('
        if ( this.mTokenLexical.PeekToken().GetTable() == 1 &&
             this.mTokenLexical.PeekToken().GetEntry() == 7 ) {
          this.mTokenLexical.GetToken() ; // Skip ')'
          return true ;
        } // if
        else {
          if ( this.Actual_Parameter_List() == true ) {
            // ************* Do the Checking Variable*********************//
          } // if
          else {
            return false ;
          } // else
        } // else
        
        if ( this.mTokenLexical.PeekToken().GetTable() == 1 &&
             this.mTokenLexical.PeekToken().GetEntry() == 7 ) { // it's ')'
          this.mTokenLexical.GetToken() ; // skip ')'
          return true ;
        } // if
        else {
          this.WhichError( this.mTokenLexical.PeekToken(), 2972 ) ;
          return false ;
        } // else
      } // if
      else if ( mErrorNum == 0 && this.mTokenLexical.PeekToken().GetTable() == 1 &&
                this.mTokenLexical.PeekToken().GetEntry() == 15 ) { // it's '['
        this.mTokenLexical.GetToken() ; // skip '['
        if ( this.CheckId( tempId ) != null ) {
          if ( this.Expression( ) == true ) {
            if ( this.mTokenLexical.PeekToken().GetTable() == 1 && 
                 this.mTokenLexical.PeekToken().GetEntry() == 16 ) { // it's ']'
              this.mTokenLexical.GetToken() ; // Skip ']'
              if ( isUnsigned == true ) { // it's unsigned_unary_exp
                TokenInfo tempPPMM = new TokenInfo() ;
                if ( this.mTokenLexical.PeekToken().GetTable()  == 1 && 
                     ( this.mTokenLexical.PeekToken().GetEntry() == 33 || 
                       this.mTokenLexical.PeekToken().GetEntry() == 34 ) ) { 
                  // it's PPMM
                  tempPPMM = this.mTokenLexical.GetToken() ; // Skip PP or MM
                } // if
              
                return true ;
              } // if
              else { // it's signed_unary_exp
                return true ;  
              } // else
            } // if
            else { // error ']'
              this.WhichError( this.mTokenLexical.PeekToken(), 3001  ) ;
              return false ;
            } // else
          } // if
          else { // error Expression
            return false ;
          } // else
        } // if
        else {
          if ( this.mErrorNum ==  0 ) {
            System.out.print( "> Line " + errorIdLine + " : " ) ;
            System.out.println( this.mErrorType[2] + "'" + tempId.GetValue() + "'" ) ;
            this.mErrorNum++ ;
            // System.out.println( "ErrorLine-->" + 3119 ) ;
          } // if
        
          return false ;  
        } // else
      } // else if
      else {
          if ( isUnsigned == true ) { // it's unsigned_unary_exp
            TokenInfo tempPPMM = new TokenInfo() ;                      
            if ( this.mTokenLexical.PeekToken().GetTable()  == 1 && 
                 ( this.mTokenLexical.PeekToken().GetEntry() == 33 || 
                   this.mTokenLexical.PeekToken().GetEntry() == 34 ) ) { 
              // it's PPMM
              if ( this.mErrorNum ==  0 && errorIdLine != 0 ) {
                System.out.print( "> Line " + errorIdLine + " : " ) ;
                System.out.println( this.mErrorType[2] + "'" + tempId.GetValue() + "'" ) ;
                this.mErrorNum++ ;
                // System.out.println( "ErrorLine-->" + 3130 ) ;
                return false ;
              } // if
              
              tempPPMM = this.mTokenLexical.GetToken() ; // Skip PP or MM
            } // if
       
            return true ;
          } // if
          else { // it's signed_unary_exp
            if ( this.mErrorNum ==  0 && errorIdLine != 0 ) {
              System.out.print( "> Line " + errorIdLine + " : " ) ;
              System.out.println( this.mErrorType[2] + "'" + tempId.GetValue() + "'" ) ;
              this.mErrorNum++ ;
              // System.out.println( "ErrorLine-->" + 3130 ) ;
              return false ;
            } // if
            
            return true ;  
          } // else         
      } // else      
    } // if
    else if (  this.mTokenLexical.PeekToken().GetTable() == 3 ||
              this.mTokenLexical.PeekToken().GetTable() == 4 || 
              this.mTokenLexical.PeekToken().GetTable() == 6 || 
              this.mTokenLexical.PeekToken().GetTable() == 7 ||
              this.mTokenLexical.PeekToken().GetTable() == 8 ) { // It's Constant
      TokenInfo tempTokenInfo = this.mTokenLexical.PeekToken() ;
      if ( tempTokenInfo.GetTable() == 7  ) {
        this.mTokenLexical.GetToken() ; // Skip Constant 
        this.mTokenLexical.GetToken() ; // Skip Constant
      } // if
    
      this.mTokenLexical.GetToken() ; // Skip Constant  
      return true ;
    } // else if
    else if ( this.mTokenLexical.PeekToken().GetTable() == 1 && 
              this.mTokenLexical.PeekToken().GetEntry() == 6 ) { // it's '('
      this.mTokenLexical.GetToken() ; // Skip '('
      if ( this.Expression() == true ) {    
        if ( this.mTokenLexical.PeekToken().GetTable() == 1 && 
             this.mTokenLexical.PeekToken().GetEntry() == 7 ) { // it's ')'
          this.mTokenLexical.GetToken() ;
          return true ;
        } // if
        else { // error ')'
          this.WhichError( this.mTokenLexical.PeekToken(), 3045 ) ;
          return false ;
        } // else
      } // if
      else { // error Expression
        return false ;
      } // else
    } // else if
  
    // if (  G.sTest == 4 ) this.mErrorNum-- ; 
    
    if ( this.mErrorNum == 0  ) {
      this.WhichError(  this.mTokenLexical.PeekToken(), 3250 ) ;  
    } // if
    else if ( this.mErrorNum == 1 && errorIdLine != 0   ) {
      System.out.print( "> Line " + errorIdLine + " : " ) ;
      System.out.println( this.mErrorType[ 2 ] + "'" + tempId.GetValue() + "'" ) ;       
       System.out.println( "3299" ) ;
    } // else if

    return false ;
  } // Signed_Unary_Exp()    
} // class Function

class SyntaxTreePL { //   Program Start Point
    
  public SyntaxTreePL( ) { 
    this.RunProgram() ;
  } // SyntaxTreePL()

  private void RunProgram() { //   use to run the program, **MAINLY FUNCTION**
    System.out.println( "Our-C running ..." ) ;
    Lexical mOgTokenLexical = new Lexical() ; // Original LexicalFile
    while ( mOgTokenLexical.GetTerminate() == false ) {
      Function main = new Function(  mOgTokenLexical, mOgTokenLexical.GetLexicalFile(),
                                     true, "main", "void" ) ;
      if (  main.Run() == true ) { 
        mOgTokenLexical = main.GetTokenLexical() ;
        // System.out.println(  "Success " +  mOgTokenLexical.GetTable5().size()  )  ;
      } // if
      else {
      // System.out.println(  "Fail " +  mOgTokenLexical.GetTable5().size()  )  ;  
      } // else
    } // while
  } // RunProgram()
  

} // class SyntaxTreePL 

class Main {
  public  static void main( String[] args ) {
    String test = G.sInput.nextLine() ;  
    G.sTest = Integer.valueOf(  test );
    SyntaxTreePL project1 = new SyntaxTreePL() ; 
  } // main()
} // class Main