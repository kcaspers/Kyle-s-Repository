class ClassOne
  def factorialCalc
    puts 'I will calculate the factorial of a number you give me.'
    userInput = Integer(gets.chomp)
    index = 0
    factorial = 1
    while index < userInput do
      factorial *= index+1
      index = index + 1
    end
    puts factorial
  end
end

factorialCalculator = ClassOne.new
factorialCalculator.factorialCalc