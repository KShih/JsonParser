## Homework 2 - Week 2

### Motivation
* Demonstrate your ability to program procedurally in Java

### Instructions
* There are three tasks to complete.


  1) Supply an implementation for the `static` method within [JsonParser](src/main/java/edu/nyu/cs9053/homework2/JsonParser.java)
  2) Supply an implementation for the `static` method within [Mechanic](src/main/java/edu/nyu/cs9053/homework2/Mechanic.java)
  3) Provide the proper `javac` arguments to ensure all `.java` files compile and are placed within a directory named `target/classes` (creating the directories if they don't exist) within the file `compile.sh`

### Testing
* Invoke the `test.sh` to test your solution

## Note
- 實作部分型態toJson函數
  - String
  - long
  - String[]

- 上述型態都是由另一個class提供資料
  - String[] 則是再由另一個class提供

### Some Corner Case
  - 千萬別 NULL.get()
  - \ "abcdefg\ " 要是某個String的內容是這樣
    - 這樣會被 \ 符號會跟 " 結合 而只剩下"abcdefg"
    - 遇到 \ " 情況 多加兩個\在他前面
    - vehicleId = vehicleId.replaceAll("\\\"", "\\\\\"");
  - else if 裡的內容是會去執行的
    - ![](assets/markdown-img-paste-20190916155720910.png)
    - if (x == null) ...
    - else if (x.get()) -> if執行完後會去執行 else if()裡的判斷式的內容, 這邊就會出問題了

### Technique to learn
  - How to deal with static constructer
  - Use __Print__ to debug in Class structure
  - convert byte to String
    - (byte_data >> (8 - (j % 8 + 1))
  - String.replaceAll("str1", "str2")
  - String.charAt(index)
  - Str.substring(0, Str.length() - 1) //刪除字串最後一個字元
  - 字串要增加一個字元, 不能用String, 要用StringBuilder
    - StringBuilder.append()
